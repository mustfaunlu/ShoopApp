package com.mustafaunlu.shoopapp.ui.shoppingList

import com.mustafaunlu.shoopapp.common.UserCartUiData
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import javax.inject.Inject

class CartUiMapper @Inject constructor() : ProductListMapper<UserCartEntity, UserCartUiData> {
    override fun map(input: List<UserCartEntity>): List<UserCartUiData> {
        return input.map {
            UserCartUiData(
                id = it.productId,
                price = it.price,
                quantity = it.quantity,
                title = it.title,
                imageUrl = it.image,
            )
        }
    }
}

class SingleCartUiMapper @Inject constructor() : ProductBaseMapper<UserCartUiData, UserCartEntity> {
    override fun map(input: UserCartUiData): UserCartEntity {
        return UserCartEntity(
            userId = 0,
            productId = input.id,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.imageUrl,
        )
    }
}
