package com.mustafaunlu.shoopapp.ui.detail

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.product.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                }

                is ScreenState.Loading -> {
                }

                is ScreenState.Success -> {
                    binding.apply {
                        productTitle.text = it.uiData.title
                        productPrice.text = "${it.uiData.price} TL"
                        productDescription.text = it.uiData.description
                        productImg.loadImage(it.uiData.imageUrl)
                        productRating.text = "Rating ${it.uiData.rating}"

                        val userId = sharedPref.getString(SHARED_PREF_USERID_KEY, SHARED_PREF_DEF)!!
                        userCart = UserCartEntity(
                            userId = userId.toInt(),
                            productId = it.uiData.id,
                            quantity = 1,
                            price = it.uiData.price.toInt(),
                            title = it.uiData.title,
                            image = it.uiData.imageUrl,
                        )
                    }
                }
            }
        }

        binding.btnAddToCart.setOnClickListener {
            detailViewModel.addToCart(userCart)
            Toast.makeText(requireContext(), getString(R.string.added_to_cart), Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnShoppingList.setOnClickListener {
            findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToShoppingListFragment())
        }
    }
}
