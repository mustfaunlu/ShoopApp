package com.mustafaunlu.shoopapp.ui.shoppingList

import ShoppingListAdapter
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.common.UserCartUiData
import com.mustafaunlu.shoopapp.databinding.FragmentShoppingListBinding
import com.mustafaunlu.shoopapp.utils.showConfirmationDialog
import com.mustafaunlu.shoopapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListBinding
    private val viewModel: ShoppingListViewModel by viewModels()
    lateinit var adapter: ShoppingListAdapter

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        val userId = sharedPref.getString(SHARED_PREF_USERID_KEY, SHARED_PREF_DEF)!!
        viewModel.getCartsByUserId(userId.toInt())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userCarts.observe(viewLifecycleOwner) { userCartState ->
            when (userCartState) {
                is ScreenState.Error -> {
                    requireView().showToast(userCartState.message)
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    adapter = ShoppingListAdapter(::onItemLongClicked).apply {
                        submitList(userCartState.uiData)
                    }
                    binding.cartListview.adapter = adapter
                }
            }
        }
    }

    private fun onItemLongClicked(userCartUiData: UserCartUiData) {
        this.showConfirmationDialog(getString(R.string.shopping_list_delete_warn)) {
            viewModel.deleteUserCartItem(userCartUiData)
            adapter.submitList(adapter.currentList.filter { it.id != userCartUiData.id })
            requireView().showToast(getString(R.string.shopping_list_item_deleted_txt))
        }
    }
}
