package com.example.final_test_01.ui.products

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _productCategory = MutableLiveData<List<ProductsItemsDto>>()
    val productCategory: LiveData<List<ProductsItemsDto>> = _productCategory

    fun getCategoriesByIds(id: String) {
        viewModelScope.launch {
            _productCategory.postValue(repository.getCategoriesByIds(1, 10, id))
            Log.e(TAG, "getProductsByCategories: $id")
        }
    }
}