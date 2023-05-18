package com.mustafaunlu.shoopapp.domain.usecase.category

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<String>>>

    operator fun invoke(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>>
}