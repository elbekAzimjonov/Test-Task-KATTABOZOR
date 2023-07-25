package com.elbek.data.resource

import com.elbek.domain.models.Product

sealed class Resource {

        data class  Success(val data: Product?): Resource()

        data class Error(val message: String): Resource()

        object Loading: Resource()

}