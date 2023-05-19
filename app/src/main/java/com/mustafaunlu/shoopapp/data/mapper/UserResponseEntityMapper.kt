package com.mustafaunlu.shoopapp.data.mapper

import com.mustafaunlu.shoopapp.data.dto.UserResponse
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserResponseEntityMapper @Inject constructor() : ProductBaseMapper<UserResponse, UserResponseEntity> {
    override fun map(input: UserResponse): UserResponseEntity {
        return UserResponseEntity(
            id = input.id,
            token = input.token,
            username = input.username,
            image = input.image,
        )
    }
}
