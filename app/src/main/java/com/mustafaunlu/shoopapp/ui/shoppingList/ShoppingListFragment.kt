package com.mustafaunlu.shoopapp.ui.shoppingList

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.databinding.FragmentShoppingListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListBinding
    private val viewModel: ShoppingListViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        val userId = sharedPref.getString(SHARED_PREF_USERID_KEY, SHARED_PREF_DEF)
        viewModel.getCartsByUserId(userId!!.toInt())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userCarts.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.cartListview.adapter = ShoppingListAdapter().apply {
                        submitList(it.uiData)
                    }
                }
            }
        }
    }
}
