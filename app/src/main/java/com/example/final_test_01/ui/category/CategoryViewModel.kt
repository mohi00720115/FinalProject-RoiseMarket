package com.example.final_test_01.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryItemsDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _category = MutableLiveData<ResponseState<List<ProductsCategoryItemsDto>>>()
    val category: LiveData<ResponseState<List<ProductsCategoryItemsDto>>> = _category

    fun getAllCategories() {
        viewModelScope.launch {
            repository.getAllCategories(1, 10).asResponseState().collect {
                _category.postValue(it)
            }
        }
    }
}