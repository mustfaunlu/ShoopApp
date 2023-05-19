package com.mustafaunlu.shoopapp.domain.usecase.category

import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.domain.entity.AllProductsEntity
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCaseImpl @Inject constructor(private val repository: ProductRepository) : CategoryUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<String>>> {
        return repository.getAllCategories()
    }

    override fun invoke(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        return repository.getProductsByCategory(categoryName)
    }
}
