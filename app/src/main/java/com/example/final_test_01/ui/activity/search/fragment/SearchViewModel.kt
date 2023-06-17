package com.example.final_test_01.ui.activity.search.fragment

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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _search = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val search: StateFlow<ResponseState<List<ProductsItemsDto>>> = _search

    private val _categorySearches = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val categorySearches: StateFlow<ResponseState<List<ProductsItemsDto>>> = _categorySearches

    private val _categoryById = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val categoryById: StateFlow<ResponseState<List<ProductsItemsDto>>> = _categoryById

    val query = MutableStateFlow<String>("")

    private var orderBy: String = "popularity"
    fun getAllProductsForSearch(search: String) {
        viewModelScope.launch {
            repository.getAllProductsForSearch(search, orderBy).asResponseState().collect {
                _search.value = it
            }
        }
    }

    private var categorySearch = "121"
    private var order = "date"
    fun getCategoryForSearch() {
        viewModelScope.launch {
            repository.getCategoryForSearch(categorySearch, order).asResponseState().collect {
                _categorySearches.value = it
            }
        }
    }

    fun changeOrderBy(str: String) {
        orderBy = str
    }

    fun getCategoriesByIds() {
        viewModelScope.launch {
            repository.getCategoriesByIds(1, 15, "52").asResponseState().collect {
                repository.getCategoriesByIds(1, 15, "havij").filter {
                    it[0].description!!.contains(query.value!!, ignoreCase = true)
                }
                _categoryById.value = it
                Log.i(TAG, "it.filter: $it")
            }
        }
    }


}