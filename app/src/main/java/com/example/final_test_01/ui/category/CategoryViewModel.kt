package com.example.final_test_01.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiState
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiStateItem
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _category = MutableLiveData<List<ProductsCategoryUiStateItem>>()
    val category: LiveData<List<ProductsCategoryUiStateItem>> = _category

    fun getProductsCategories() {
        viewModelScope.launch {
            _category.postValue(repository.getProductsCategories(1,10))
        }
    }
}