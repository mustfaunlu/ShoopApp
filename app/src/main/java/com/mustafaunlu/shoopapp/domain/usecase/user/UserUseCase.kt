package com.mustafaunlu.shoopapp.domain.usecase.user

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    operator fun invoke(user: User): Flow<NetworkResponseState<UserResponseEntity>>
}
