package com.elbek.kattabozor.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.elbek.data.resource.Resource
import com.elbek.domain.models.Product
import com.elbek.kattabozor.databinding.ActivityMainBinding
import com.elbek.kattabozor.presentation.adapter.ProductAdapter
import com.elbek.kattabozor.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var view: ActivityMainBinding
    lateinit var adapter: ProductAdapter
    lateinit var progressBar: ProgressBar

    private val viewModel: ProductViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        progressBar = view.progress
        launch {
            viewModel.getProductsFlow().collect {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        setView(it.data!!)
                    }

                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            "Something Went Wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setView(product: Product) {
        adapter = ProductAdapter(product)
        view.productList.adapter = adapter
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}