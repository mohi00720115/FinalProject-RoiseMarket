package com.example.final_test_01.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.ResponseState.Loading
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _newestProduct = MutableStateFlow<ResponseState<ProductsDto>>(Loading)
    val newestProduct: StateFlow<ResponseState<ProductsDto>> = _newestProduct

    private val _mostVisitedProduct = MutableStateFlow<ResponseState<ProductsDto>>(Loading)
    val mostVisitedProduct: StateFlow<ResponseState<ProductsDto>> = _mostVisitedProduct

    private val _topRatedProduct = MutableStateFlow<ResponseState<ProductsDto>>(Loading)
    val topRatedProduct: StateFlow<ResponseState<ProductsDto>> = _topRatedProduct

    //فروش ویژه
    private val _onSellProducts = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val onSellProducts: StateFlow<ResponseState<List<ProductsItemsDto>>> = _onSellProducts

    init {
        getNewestProducts()
        getMostVisitedProducts()
        getTopRatedProducts()
    }

    private fun getNewestProducts() {
        viewModelScope.launch {
            repository.getNewestProducts(1, 14).asResponseState().collect {
                _newestProduct.value = it
            }
        }
    }

    private fun getMostVisitedProducts() {
        viewModelScope.launch {
            repository.getMostVisitedProducts(1, 15).asResponseState().collect {
                _mostVisitedProduct.value = it
            }
        }
    }

    private fun getTopRatedProducts() {
        viewModelScope.launch {
            repository.getTopRatedProducts(1, 15).asResponseState().collect {
                _topRatedProduct.value = it
            }
        }
    }

    fun getIdsCategoryForViewPager() {
        viewModelScope.launch {
            repository.getIdsCategoryForViewPager(1, 15).asResponseState().collect {
                _onSellProducts.value = it
            }
        }
    }
}