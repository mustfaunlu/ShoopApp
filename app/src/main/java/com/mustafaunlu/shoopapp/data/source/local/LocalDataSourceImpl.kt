package com.mustafaunlu.shoopapp.data.source.local

import com.mustafaunlu.shoopapp.data.database.CartDao
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val cartDao: CartDao) : LocalDataSource {
    override suspend fun getCartByUserId(userId: Int): List<UserCartEntity> {
        return cartDao.getCartByUserId(userId)
    }

    override suspend fun insertUserCart(userCartEntity: UserCartEntity) {
        cartDao.insertUserCart(userCartEntity)
    }
}
