package com.mustafaunlu.shoopapp.ui.shoppingList

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.UserCartUiData

class ShoppingListAdapter(
    context: Context,
    resource: Int,
    objects: List<UserCartUiData>,
) : ArrayAdapter<UserCartUiData>(context, resource, objects) {

    @SuppressLint("SetTextI18n")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.shopping_list_item, parent, false)
        }

        val cart = getItem(position)
        val productNameTextView = view!!.findViewById<TextView>(R.id.itemNameTextView)
        val productPriceTextView = view!!.findViewById<TextView>(R.id.itemPriceTextView)
        val productId = view!!.findViewById<TextView>(R.id.itemId)
        val productQuantity = view!!.findViewById<TextView>(R.id.itemQuantity)

        if (cart != null) {
            productNameTextView.text = cart.title
            productPriceTextView.text = "${cart.price} TL"
            productId.text = "Product Id: ${cart.id}"
            productQuantity.text = "Quantity: ${cart.quantity}"
        }

        return view
    }
}
