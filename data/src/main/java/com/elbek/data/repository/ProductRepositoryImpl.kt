package com.elbek.data.repository

import com.elbek.data.network.api.ApiService
import com.elbek.domain.models.Product
import com.elbek.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProductRepository {
    override fun getProducts(): Flow<Product> {
        return apiService.getProducts()
    }
}