package com.mustafaunlu.shoopapp.domain.usecase.cart.delete_cart

import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteUserCartUseCaseImpl @Inject constructor(private val repository: ProductRepository) : DeleteUserCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.deleteUserCartItem(userCartEntity)
    }
}