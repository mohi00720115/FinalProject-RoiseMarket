package com.example.final_test_01.ui.products

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ProductsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _productCategory = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val productCategory: StateFlow<ResponseState<List<ProductsItemsDto>>> = _productCategory

    fun getCategoriesByIds(id: String) {
        viewModelScope.launch {
            repository.getCategoriesByIds(1, 15, id).asResponseState().collect {
                _productCategory.value = it
                Log.e(TAG, "getProductsByCategories: $id")
            }
        }
    }
}