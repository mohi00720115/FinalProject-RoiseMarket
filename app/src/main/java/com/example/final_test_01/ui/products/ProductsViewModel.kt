package com.example.final_test_01.ui.products

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiStateItem
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _productCategory = MutableLiveData<List<ProductsUiStateItem>>()
    val productCategory: LiveData<List<ProductsUiStateItem>> = _productCategory

    fun getProductsByCategories(id: String) {
        viewModelScope.launch {
            _productCategory.postValue(repository.getProductsByCategories(1, 10, id))
            Log.e(TAG, "getProductsByCategories: $id")
        }
    }
}