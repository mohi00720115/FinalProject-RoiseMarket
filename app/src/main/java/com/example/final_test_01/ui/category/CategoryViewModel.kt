package com.example.final_test_01.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
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
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _category = MutableStateFlow<ResponseState<List<ProductsCategoryItemsDto>>>(Loading)
    val category: StateFlow<ResponseState<List<ProductsCategoryItemsDto>>> = _category

    fun getAllCategories() {
        viewModelScope.launch {
            repository.getAllCategories(1, 15).asResponseState().collect {
                _category.value = it
            }
        }
    }
}