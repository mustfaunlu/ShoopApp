package com.mustafaunlu.shoopapp.domain.usecase.single

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import kotlinx.coroutines.flow.Flow

interface GetSingleProductUseCase {
    operator fun invoke(id: Int): Flow<NetworkResponseState<SingleProductEntity>>
}
