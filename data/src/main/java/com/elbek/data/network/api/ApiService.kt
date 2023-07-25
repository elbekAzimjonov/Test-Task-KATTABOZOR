package com.elbek.data.network.api

import com.elbek.domain.models.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
     @GET("offers")
     fun getProducts():Flow<Product>
}