package com.mustafaunlu.shoopapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.databinding.FragmentHomeBinding
import com.mustafaunlu.shoopapp.utils.showToast
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
                    binding.productRv.adapter = ProductAdapter { productId ->
                        navigateToProductDetail(productId)
                    }
                    (binding.productRv.adapter as ProductAdapter).submitList(it.uiData)
                }
            }
        }

        homeViewModel.categories.observe(viewLifecycleOwner) { homepageState ->
            when (homepageState) {
                is ScreenState.Error -> {
                    requireView().showToast(homepageState.message)
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.rvCategory.adapter = CategoryAdapter(
                        homepageState.uiData,
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
