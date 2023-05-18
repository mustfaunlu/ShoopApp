package com.mustafaunlu.shoopapp.domain.entity

data class UserResponseEntity(
    val id: Int,
    val token: String,
    val username: String,
    val image: String,
)
