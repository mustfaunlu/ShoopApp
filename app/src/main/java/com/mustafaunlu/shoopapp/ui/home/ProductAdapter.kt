package com.mustafaunlu.shoopapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.AllProductsUiData
import com.mustafaunlu.shoopapp.utils.loadImage

class ProductAdapter(
    context: Context,
    resource: Int,
    objects: List<AllProductsUiData>,
    private val onItemClicked: (AllProductsUiData) -> Unit,
) : ArrayAdapter<AllProductsUiData>(context, resource, objects) {

    @SuppressLint("SetTextI18n")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.product_item, parent, false)
        }

        val product = getItem(position)
        val productImage = view!!.findViewById<ImageView>(R.id.product_img)
        val productNameTextView = view!!.findViewById<TextView>(R.id.product_title)
        val productDescription = view!!.findViewById<TextView>(R.id.product_description)
        val productPriceTextView = view!!.findViewById<TextView>(R.id.product_price)

        productNameTextView.text = product?.title
        productPriceTextView.text = "${product?.price} TL"
        productDescription.text = product?.description
        productImage.loadImage(product!!.imageUrl)

        view.setOnClickListener {
            onItemClicked(product)
        }

        return view
    }
}
