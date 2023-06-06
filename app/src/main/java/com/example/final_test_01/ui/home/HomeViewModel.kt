package com.example.final_test_01.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_dto.ProductsUiState
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _newestProduct = MutableLiveData<ProductsUiState>()
    val newestProduct: LiveData<ProductsUiState> = _newestProduct

    private val _mostVisitedProduct = MutableLiveData<ProductsUiState>()
    val mostVisitedProduct: LiveData<ProductsUiState> = _mostVisitedProduct

    private val _topRatedProduct = MutableLiveData<ProductsUiState>()
    val topRatedProduct: LiveData<ProductsUiState> = _topRatedProduct

    init {
        getNewestProducts()
        getMostVisitedProducts()
        getTopRatedProducts()
    }

    private fun getNewestProducts() {
        viewModelScope.launch {
            _newestProduct.postValue(repository.getNewestProducts(1, 10))
        }
    }

    private fun getMostVisitedProducts() {
        viewModelScope.launch {
            _mostVisitedProduct.postValue(repository.getMostVisitedProducts(1, 10))
        }
    }

    private fun getTopRatedProducts() {
        viewModelScope.launch {
            _topRatedProduct.postValue(repository.getTopRatedProducts(1, 10))
        }
    }


}