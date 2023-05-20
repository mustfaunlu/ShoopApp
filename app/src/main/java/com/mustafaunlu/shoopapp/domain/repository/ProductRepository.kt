package com.mustafaunlu.shoopapp.domain.repository

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.CartRequest
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<NetworkResponseState<List<AllProductsEntity>>>

    fun getProductById(productId: Int): Flow<NetworkResponseState<SingleProductEntity>>

    fun addToCart(cartRequest: CartRequest): Flow<NetworkResponseState<List<UserCartEntity>>>

    fun login(user: User): Flow<NetworkResponseState<UserResponseEntity>>

    fun getAllCategories(): Flow<NetworkResponseState<List<String>>>

    fun getProductsByCategory(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>>

    fun getCartsByUserId(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend fun insertCartToDb(userCartEntity: UserCartEntity)
}
