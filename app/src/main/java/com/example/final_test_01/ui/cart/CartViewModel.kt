package com.example.final_test_01.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _cartList = MutableLiveData<ResponseState<List<ProductsItemsDto>>>()
    val cartList: LiveData<ResponseState<List<ProductsItemsDto>>> = _cartList

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            repository.getItemsIdsProducts(id).asResponseState().collect {
                _cartList.postValue(it)
            }
        }
    }
}