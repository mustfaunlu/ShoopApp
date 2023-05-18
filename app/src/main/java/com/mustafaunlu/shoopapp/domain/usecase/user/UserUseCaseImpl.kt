package com.mustafaunlu.shoopapp.domain.usecase.user

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : UserUseCase {
    override fun invoke(user: User): Flow<NetworkResponseState<UserResponseEntity>> {
        return repository.login(user)
    }
}
