package com.mustafaunlu.shoopapp.data.source.local

import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity

interface LocalDataSource {
    suspend fun getCartByUserId(userId: Int): List<UserCartEntity>

    suspend fun insertUserCart(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartItem(userCartEntity: UserCartEntity)
}
