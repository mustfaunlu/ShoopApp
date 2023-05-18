package com.mustafaunlu.shoopapp.domain.usecase.cart

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.CartRequest
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val repository: ProductRepository) : CartUseCase {
    override fun invoke(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> = repository.getCartsByUserId(userId)
    override fun invoke(cartRequest: CartRequest): Flow<NetworkResponseState<List<UserCartEntity>>> {
        return repository.addToCart(cartRequest)
    }
}