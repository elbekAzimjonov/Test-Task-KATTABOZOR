package com.elbek.kattabozor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbek.domain.usecase.ProductUseCase
import com.elbek.data.resource.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {
    fun getProductsFlow(): StateFlow<Resource> {
        val flow = MutableStateFlow<Resource>(Resource.Loading)
        viewModelScope.launch {
            productUseCase.getProduct().catch {
                flow.emit(Resource.Error("Something went wrong"))
            }.collect{
                if (it.isSuccess) {
                    flow.emit(Resource.Success(it.getOrNull()))
                } else if (it.isFailure) {
                    flow.emit(Resource.Error("Something went wrong"))
                }
            }
        }
        return flow
    }


}