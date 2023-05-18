package com.mustafaunlu.shoopapp.ui.login

import com.mustafaunlu.shoopapp.common.UserUiData
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserUiMapper @Inject constructor() : ProductBaseMapper<UserResponseEntity, UserUiData> {
    override fun map(input: UserResponseEntity): UserUiData {
        return UserUiData(
            id = input.id,
            token = input.token,
            username = input.username,
            image = input.image,
        )
    }
}
