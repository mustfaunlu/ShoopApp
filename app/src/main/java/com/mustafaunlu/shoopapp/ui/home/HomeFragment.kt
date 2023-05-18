package com.mustafaunlu.shoopapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.productListview.adapter = ProductAdapter(
                        requireContext(),
                        R.layout.product_item,
                        it.uiData,
                    ) {
                        navigateToProductDetail(it.id)
                    }
                }
            }
        }

        homeViewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.rvCategory.adapter = CategoryAdapter(
                        it.uiData,
                    ) { categoryName ->
                        getCategoryByName(categoryName)
                    }
                }
            }
        }
    }

    private fun navigateToProductDetail(productId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(productId)
        findNavController().navigate(action)
    }

    private fun getCategoryByName(categoryName: String) {
        homeViewModel.getProductsByCategory(categoryName)
    }
}
