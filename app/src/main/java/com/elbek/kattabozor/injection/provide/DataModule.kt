package com.elbek.kattabozor.injection.provide

import com.elbek.data.network.api.ApiService
import com.elbek.data.repository.ProductRepositoryImpl
import com.elbek.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindModule {
        @Binds
        abstract fun bindApiRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
    }
}