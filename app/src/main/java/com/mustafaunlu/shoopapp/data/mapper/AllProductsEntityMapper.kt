package com.mustafaunlu.shoopapp.data.mapper

import com.mustafaunlu.shoopapp.data.dto.Product

import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import javax.inject.Inject

class AllProductsEntityMapper @Inject constructor() : ProductListMapper<Product, AllProductsEntity> {
    override fun map(input: List<Product>): List<AllProductsEntity> {
        return input.map {
            AllProductsEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price.toString(),
                imageUrl = it.images[0],
            )
        }
    }
}