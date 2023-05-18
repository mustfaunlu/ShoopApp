package com.mustafaunlu.shoopapp.domain.mapper

interface ProductBaseMapper<I, O> {
    fun map(input: I): O
}
