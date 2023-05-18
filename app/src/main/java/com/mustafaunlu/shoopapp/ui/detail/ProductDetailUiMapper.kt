package com.mustafaunlu.shoopapp.ui.detail

import com.mustafaunlu.shoopapp.common.SingleProductUiData
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class ProductDetailUiMapper @Inject constructor() : ProductBaseMapper<SingleProductEntity, SingleProductUiData> {
    override fun map(input: SingleProductEntity): SingleProductUiData {
        return SingleProductUiData(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price,
            imageUrl = input.imageUrl,
            rating = input.rating,
        )
    }
}
