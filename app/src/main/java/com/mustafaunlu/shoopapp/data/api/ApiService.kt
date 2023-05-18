package com.mustafaunlu.shoopapp.data.api

import com.mustafaunlu.shoopapp.data.dto.CartRequest
import com.mustafaunlu.shoopapp.data.dto.CartResponse
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.Products
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserCartResponse
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Products

    @GET("products/{id}")
    suspend fun getProductsById(@Path("id") productId: Int): Product

    @POST("carts/add")
    suspend fun addToCart(@Body request: CartRequest): CartResponse

    @GET("carts/user/{userId}")
    suspend fun getCartByUserId(@Path("userId") userId: Int): UserCartResponse

    @POST("auth/login")
    suspend fun login(@Body user: User): UserResponse

    @GET("products/categories")
    suspend fun getAllCategories(): List<String>

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategory(@Path("categoryName") categoryName: String): Products
}
