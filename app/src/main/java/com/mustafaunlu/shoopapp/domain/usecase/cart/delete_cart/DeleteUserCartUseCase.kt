package com.mustafaunlu.shoopapp.domain.usecase.cart.delete_cart

import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity

interface DeleteUserCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
