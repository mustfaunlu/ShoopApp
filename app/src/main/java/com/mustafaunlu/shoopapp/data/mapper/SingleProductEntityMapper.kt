package com.mustafaunlu.shoopapp.data.mapper

import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class SingleProductEntityMapper @Inject constructor() : ProductBaseMapper<Product, SingleProductEntity> {
    override fun map(input: Product): SingleProductEntity {
        return SingleProductEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price.toString(),
            imageUrl = input.images[0],
            rating = input.rating.toString(),
        )
    }
}
