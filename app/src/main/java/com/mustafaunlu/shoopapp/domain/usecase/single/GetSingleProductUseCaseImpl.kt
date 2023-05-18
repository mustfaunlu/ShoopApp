package com.mustafaunlu.shoopapp.domain.usecase.single

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<NetworkResponseState<SingleProductEntity>> = repository.getProductById(id)
}