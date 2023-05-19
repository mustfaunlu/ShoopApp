package com.mustafaunlu.shoopapp.di

import com.mustafaunlu.shoopapp.data.repository.ProductRepositoryImpl
import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl,
    ): ProductRepository
}
