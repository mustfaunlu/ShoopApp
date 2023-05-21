package com.mustafaunlu.shoopapp.data.source.remote

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.Products
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllProducts(): Flow<NetworkResponseState<Products>>

    fun getProductById(productId: Int): Flow<NetworkResponseState<Product>>

    fun login(user: User): Flow<NetworkResponseState<UserResponse>>

    fun getAllCategories(): Flow<NetworkResponseState<List<String>>>

    fun getProductsByCategory(categoryName: String): Flow<NetworkResponseState<Products>>
}
