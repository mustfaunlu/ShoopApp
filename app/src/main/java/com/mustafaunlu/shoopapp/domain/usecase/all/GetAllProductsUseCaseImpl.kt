package com.mustafaunlu.shoopapp.domain.usecase.all

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetAllProductsUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<AllProductsEntity>>> = repository.getAllProducts()
}