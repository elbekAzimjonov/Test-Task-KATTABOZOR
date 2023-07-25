package com.elbek.domain.repository

import com.elbek.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Product>
}