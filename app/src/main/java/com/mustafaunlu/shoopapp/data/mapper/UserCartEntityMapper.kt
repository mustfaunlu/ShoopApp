package com.mustafaunlu.shoopapp.data.mapper

import com.mustafaunlu.shoopapp.data.dto.CartResponseProduct
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import javax.inject.Inject

class UserCartEntityMapper @Inject constructor() : ProductListMapper<CartResponseProduct, UserCartEntity> {
    override fun map(input: List<CartResponseProduct>): List<UserCartEntity> {
        return input.map {
            UserCartEntity(
                id = it.id,
                price = it.price,
                quantity = it.quantity,
                title = it.title,
            )
        }
    }
}
