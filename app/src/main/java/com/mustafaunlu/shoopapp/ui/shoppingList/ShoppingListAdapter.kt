package com.mustafaunlu.shoopapp.ui.shoppingList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.shoopapp.common.UserCartUiData
import com.mustafaunlu.shoopapp.databinding.ShoppingListItemBinding

class ShoppingListAdapter : ListAdapter<UserCartUiData, ShoppingListAdapter.ShoppingListViewHolder>(ShoppingListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ShoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ShoppingListViewHolder(private val binding: ShoppingListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cart: UserCartUiData) {
            binding.apply {
                itemNameTextView.text = cart.title
                itemPriceTextView.text = "${cart.price} TL"
                itemId.text = "Product Id: ${cart.id}"
                itemQuantity.text = "Quantity: ${cart.quantity}"
            }
        }
    }

    private class ShoppingListDiffCallback : DiffUtil.ItemCallback<UserCartUiData>() {
        override fun areItemsTheSame(oldItem: UserCartUiData, newItem: UserCartUiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserCartUiData, newItem: UserCartUiData): Boolean {
            return oldItem == newItem
        }
    }
}
