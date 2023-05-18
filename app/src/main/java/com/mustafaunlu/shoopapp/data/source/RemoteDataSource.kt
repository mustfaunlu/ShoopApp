package com.mustafaunlu.shoopapp.data.source

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.CartRequest
import com.mustafaunlu.shoopapp.data.dto.CartResponse
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.Products
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserCartResponse
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllProducts(): Flow<NetworkResponseState<Products>>

    fun getProductById(productId: Int): Flow<NetworkResponseState<Product>>

    fun addToCart(cartRequest: CartRequest): Flow<NetworkResponseState<CartResponse>>

    fun login(user: User): Flow<NetworkResponseState<UserResponse>>

    fun getAllCategories(): Flow<NetworkResponseState<List<String>>>

    fun getProductsByCategory(categoryName: String): Flow<NetworkResponseState<Products>>

    fun getCartsByUserId(userId: Int): Flow<NetworkResponseState<UserCartResponse>>
}
