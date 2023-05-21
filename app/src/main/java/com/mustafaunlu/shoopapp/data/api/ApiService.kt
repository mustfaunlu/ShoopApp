package com.mustafaunlu.shoopapp.data.api

import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.Products
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET(PRODUCTS)
    suspend fun getProducts(): Products

    @GET(PRODUCTS_ID)
    suspend fun getProductsById(@Path(ID) productId: Int): Product

    @POST(AUTH_LOGIN)
    suspend fun login(@Body user: User): UserResponse

    @GET(PRODUCTS_CATEGORIES)
    suspend fun getAllCategories(): List<String>

    @GET(PRODUCTS_CATEGORY)
    suspend fun getProductsByCategory(@Path(CATEGORY_NAME) categoryName: String): Products

    companion object {
        const val PRODUCTS = "products"
        const val ID = "id"
        const val PRODUCTS_ID = "products/{$ID}"
        const val AUTH_LOGIN = "auth/login"
        const val PRODUCTS_CATEGORIES = "products/categories"
        const val CATEGORY_NAME = "categoryName"
        const val PRODUCTS_CATEGORY = "products/category/{$CATEGORY_NAME}"
    }
}
