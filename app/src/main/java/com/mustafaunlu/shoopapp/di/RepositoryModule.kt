package com.mustafaunlu.shoopapp.di

import com.mustafaunlu.shoopapp.domain.repository.ProductRepository
import com.mustafaunlu.shoopapp.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLoginRepository(
        repository: ProductRepositoryImpl,
    ): ProductRepository
}
