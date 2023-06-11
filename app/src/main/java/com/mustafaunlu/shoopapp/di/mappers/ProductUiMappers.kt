package com.mustafaunlu.shoopapp.di.mappers

import com.mustafaunlu.shoopapp.common.AllProductsUiData
import com.mustafaunlu.shoopapp.common.SingleProductUiData
import com.mustafaunlu.shoopapp.common.UserCartUiData
import com.mustafaunlu.shoopapp.common.UserUiData
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import com.mustafaunlu.shoopapp.ui.detail.ProductDetailUiMapper
import com.mustafaunlu.shoopapp.ui.home.ProductHomeUiMapper
import com.mustafaunlu.shoopapp.ui.login.UserUiMapper
import com.mustafaunlu.shoopapp.ui.shoppingList.CartUiMapper
import com.mustafaunlu.shoopapp.ui.shoppingList.SingleCartUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductUiMappers {
    @Binds
    @ViewModelScoped
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductHomeUiMapper): ProductListMapper<AllProductsEntity, AllProductsUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindDetailProductUiMapper(productUiDataMapper: ProductDetailUiMapper): ProductBaseMapper<SingleProductEntity, SingleProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCartUiMapper(cartUiDataMapper: CartUiMapper): ProductListMapper<UserCartEntity, UserCartUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartUiMapper(singleCartUiDataMapper: SingleCartUiMapper): ProductBaseMapper<UserCartUiData, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserUiMapper(userUiDataMapper: UserUiMapper): ProductBaseMapper<UserResponseEntity, UserUiData>
}
