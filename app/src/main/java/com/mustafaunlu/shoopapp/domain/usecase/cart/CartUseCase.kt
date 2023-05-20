package com.mustafaunlu.shoopapp.domain.usecase.cart

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    operator fun invoke(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
