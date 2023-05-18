package com.mustafaunlu.shoopapp.di

import com.mustafaunlu.shoopapp.data.dto.CartResponseProduct
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import com.mustafaunlu.shoopapp.data.mapper.AllProductsEntityMapper
import com.mustafaunlu.shoopapp.data.mapper.SingleProductEntityMapper
import com.mustafaunlu.shoopapp.data.mapper.UserCartEntityMapper
import com.mustafaunlu.shoopapp.data.mapper.UserResponseEntityMapper
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAllProductsEntityMapper(allProductsEntityMapper: AllProductsEntityMapper): ProductListMapper<Product, AllProductsEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleProductEntityMapper(singleProductEntityMapper: SingleProductEntityMapper): ProductBaseMapper<Product, SingleProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartEntityMapper(userCartEntityMapper: UserCartEntityMapper): ProductListMapper<CartResponseProduct, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserResponseEntityMapper(userResponseEntityMapper: UserResponseEntityMapper): ProductBaseMapper<UserResponse, UserResponseEntity>
}
