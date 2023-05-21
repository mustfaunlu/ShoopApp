package com.mustafaunlu.shoopapp.data.source.remote

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.api.ApiService
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.Products
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
    override fun getAllProducts(): Flow<NetworkResponseState<Products>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getProducts()
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getProductById(productId: Int): Flow<NetworkResponseState<Product>> {
        return flow {
            try {
                emit(NetworkResponseState.Loading)
                val response = apiService.getProductsById(productId)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun login(user: User): Flow<NetworkResponseState<UserResponse>> {
        return flow {
            try {
                emit(NetworkResponseState.Loading)
                val response = apiService.login(user)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getAllCategories(): Flow<NetworkResponseState<List<String>>> {
        return flow {
            try {
                emit(NetworkResponseState.Loading)
                val response = apiService.getAllCategories()
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getProductsByCategory(categoryName: String): Flow<NetworkResponseState<Products>> {
        return flow {
            try {
                emit(NetworkResponseState.Loading)
                val response = apiService.getProductsByCategory(categoryName)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }
}
