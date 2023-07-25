package com.elbek.kattabozor.injection.provide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elbek.kattabozor.presentation.viewmodel.ProductViewModel
import com.elbek.kattabozor.presentation.viewmodel.ViewModelFactory
import com.elbek.kattabozor.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindMainViewModel(viewModel: ProductViewModel): ViewModel
}