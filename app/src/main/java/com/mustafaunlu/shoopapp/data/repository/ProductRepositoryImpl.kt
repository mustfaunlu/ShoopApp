package com.mustafaunlu.shoopapp.data.repository

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.CartRequest
import com.mustafaunlu.shoopapp.data.dto.CartResponseProduct
import com.mustafaunlu.shoopapp.data.dto.Product
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.data.dto.UserResponse
import com.mustafaunlu.shoopapp.data.source.LocalDataSource
import com.mustafaunlu.shoopapp.data.source.RemoteDataSource
import com.mustafaunlu.shoopapp.di.IoDispatcher
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.entity.SingleProductEntity
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import com.mustafaunlu.shoopapp.utils.mapResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val singleProductMapper: ProductBaseMapper<Product, SingleProductEntity>,
    private val allProductsMapper: ProductListMapper<Product, AllProductsEntity>,
    private val cartMapper: ProductListMapper<CartResponseProduct, UserCartEntity>,
    private val userResponseEntityMapper: ProductBaseMapper<UserResponse, UserResponseEntity>,
) : ProductRepository {
    override fun getAllProducts(): Flow<NetworkResponseState<List<AllProductsEntity>>> =
        remoteDataSource.getAllProducts().map {
            it.mapResponse {
                allProductsMapper.map(products)
            }
        }

    override fun getProductById(productId: Int): Flow<NetworkResponseState<SingleProductEntity>> =
        remoteDataSource.getProductById(productId).map {
            it.mapResponse {
                singleProductMapper.map(this)
            }
        }

    override fun addToCart(cartRequest: CartRequest): Flow<NetworkResponseState<List<UserCartEntity>>> {
        return remoteDataSource.addToCart(cartRequest).map { cartResponse ->
            cartResponse.mapResponse {
                cartMapper.map(products)
            }
        }
    }

    override fun login(user: User): Flow<NetworkResponseState<UserResponseEntity>> {
        return remoteDataSource.login(user).map {
            it.mapResponse {
                userResponseEntityMapper.map(this)
            }
        }.flowOn(ioDispatcher)
    }

    override fun getAllCategories(): Flow<NetworkResponseState<List<String>>> =
        remoteDataSource.getAllCategories().map {
            it.mapResponse {
                this
            }
        }

    override fun getProductsByCategory(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        return remoteDataSource.getProductsByCategory(categoryName).map {
            it.mapResponse {
                allProductsMapper.map(products)
            }
        }
    }

    override fun getCartsByUserId(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> =
        remoteDataSource.getCartsByUserId(userId).map {
            var cartProducts: List<CartResponseProduct> = listOf()
            it.mapResponse {
                this.carts.forEach { cart ->
                    cartProducts = cart.products
                }
                cartMapper.map(cartProducts)
            }
        }

    override fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> {
        return flow {
            emit(NetworkResponseState.Success(localDataSource.getCartByUserId(userId)))
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertCartToDb(userCartEntity: UserCartEntity) {
        localDataSource.insertUserCart(userCartEntity)
    }
}
