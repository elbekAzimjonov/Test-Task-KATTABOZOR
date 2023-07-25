package com.elbek.domain.usecase

import com.elbek.domain.models.Product
import com.elbek.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun getProduct(): Flow<Result<Product>> {
        return productRepository.getProducts().map { Result.success(it) }
            .catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)
    }
    }