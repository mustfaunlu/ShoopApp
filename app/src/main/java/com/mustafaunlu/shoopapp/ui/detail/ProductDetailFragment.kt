package com.mustafaunlu.shoopapp.ui.detail

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.databinding.FragmentProductDetailBinding
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.utils.loadImage
import com.mustafaunlu.shoopapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var userCart: UserCartEntity

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        detailViewModel.getProduct(args.productId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductDetail()
        setupAddToCartButton()
        setupShoppingListButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupProductDetail() {
        detailViewModel.product.observe(viewLifecycleOwner) { productState ->
            when (productState) {
                is ScreenState.Error -> {
                    requireView().showToast(productState.message)
                }

                is ScreenState.Success -> {
                    val product = productState.uiData
                    binding.apply {
                        productTitle.text = product.title
                        productPrice.text = "${product.price} TL"
                        productDescription.text = product.description
                        productImg.loadImage(product.imageUrl)
                        productRating.text = "Rating ${product.rating}"

                        val userId = sharedPref.getString(
                            SHARED_PREF_USERID_KEY,
                            SHARED_PREF_DEF,
                        ) ?: SHARED_PREF_DEF
                        userCart = UserCartEntity(
                            userId = userId.toInt(),
                            productId = product.id,
                            quantity = 1,
                            price = product.price.toInt(),
                            title = product.title,
                            image = product.imageUrl,
                        )
                    }
                }

                else -> {}
            }
        }
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            detailViewModel.addToCart(userCart)
            requireView().showToast(getString(R.string.added_to_cart))
        }
    }

    private fun setupShoppingListButton() {
        binding.btnShoppingList.setOnClickListener {
            findNavController().navigate(
                ProductDetailFragmentDirections.actionProductDetailFragmentToShoppingListFragment(),
            )
        }
    }
}
