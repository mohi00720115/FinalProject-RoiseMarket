package com.example.final_test_01.ui.cart

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.local.LineItemEntity
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
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _cartList = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val cartList: StateFlow<ResponseState<List<ProductsItemsDto>>> = _cartList

    private val _product = MutableLiveData<List<LineItemEntity>>()
    val product: LiveData<List<LineItemEntity>> = _product

    private val productList = mutableListOf<ProductsItemsDto>()

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            repository.getItemsIdsProducts(id).asResponseState().collect {
                _cartList.value = it
                Log.e(TAG, "_cartList.value: $it")
            }
        }
    }

    /**
     * تک محصول رو می گیره و اضافه می کنه به یک لیست
     */
    fun addItemToList(productItem: List<ProductsItemsDto>): List<ProductsItemsDto> {
        productList += productItem
        return productList
    }



}