package com.example.final_test_01.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_dto.ProductsDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _newestProduct = MutableLiveData<ResponseState<ProductsDto>>()
    val newestProduct: LiveData<ResponseState<ProductsDto>> = _newestProduct

    private val _mostVisitedProduct = MutableLiveData<ResponseState<ProductsDto>>()
    val mostVisitedProduct: LiveData<ResponseState<ProductsDto>> = _mostVisitedProduct

    private val _topRatedProduct = MutableLiveData<ResponseState<ProductsDto>>()
    val topRatedProduct: LiveData<ResponseState<ProductsDto>> = _topRatedProduct

    init {
        getNewestProducts()
        getMostVisitedProducts()
        getTopRatedProducts()
    }

    private fun getNewestProducts() {
        viewModelScope.launch {
            repository.getNewestProducts(1, 15).asResponseState().collect {
                _newestProduct.postValue(it)
            }
        }
    }

    private fun getMostVisitedProducts() {
        viewModelScope.launch {
            repository.getMostVisitedProducts(1, 15).asResponseState().collect {
                _mostVisitedProduct.postValue(it)
            }
        }
    }

    private fun getTopRatedProducts() {
        viewModelScope.launch {
            repository.getTopRatedProducts(1, 15).asResponseState().collect {
                _topRatedProduct.postValue(it)
            }
        }
    }


}