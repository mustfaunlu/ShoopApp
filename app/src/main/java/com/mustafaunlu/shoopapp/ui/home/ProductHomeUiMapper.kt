package com.mustafaunlu.shoopapp.ui.home

import com.mustafaunlu.shoopapp.common.AllProductsUiData
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import javax.inject.Inject

class ProductHomeUiMapper @Inject constructor() :
    ProductListMapper<AllProductsEntity, AllProductsUiData> {
    override fun map(input: List<AllProductsEntity>): List<AllProductsUiData> {
        return input.map {
            AllProductsUiData(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
            )
        }
    }
}
