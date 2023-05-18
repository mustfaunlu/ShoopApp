package com.mustafaunlu.shoopapp.di

import com.mustafaunlu.shoopapp.data.source.RemoteDataSource
import com.mustafaunlu.shoopapp.data.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDataSource(
        dataSource: RemoteDataSourceImpl,
    ): RemoteDataSource
}
