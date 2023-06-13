package com.example.final_test_01.ui.activity.search.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _search = MutableLiveData<ResponseState<List<ProductsItemsDto>>>()
    val search: LiveData<ResponseState<List<ProductsItemsDto>>> = _search

    private val _categoryById = MutableLiveData<ResponseState<List<ProductsItemsDto>>>()
    val categoryById: LiveData<ResponseState<List<ProductsItemsDto>>> = _categoryById

    private var orderBy: String = "popularity"
    fun getAllProductsForSearch(search: String) {
        viewModelScope.launch {
            repository.getAllProductsForSearch(search, orderBy).asResponseState().collect {
                _search.postValue(it)
            }
        }
    }

    fun changeOrderBy(str: String) {
        orderBy = str
    }

}