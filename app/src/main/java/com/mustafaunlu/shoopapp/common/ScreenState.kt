package com.mustafaunlu.shoopapp.common

sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Error(val message: String) : ScreenState<Nothing>()
    data class Success<out T : Any>(val uiData: T) : ScreenState<T>()
}
data class AllProductsUiData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
)

data class SingleProductUiData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val rating: String,
)

data class UserCartUiData(
    val id: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val imageUrl: String,
)

data class UserUiData(
    val id: Int,
    val token: String,
    val username: String,
    val image: String,
)
