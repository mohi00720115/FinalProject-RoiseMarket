package com.example.final_test_01.ui.detail

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.local.LineItemEntity
import com.example.final_test_01.data.model.dto.order.LineItem
import com.example.final_test_01.data.model.dto.order.OrderDto
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
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _itemID = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
    val itemID: StateFlow<ResponseState<List<ProductsItemsDto>>> = _itemID

    private val _order = MutableLiveData<ResponseState<OrderDto>>()
    val order: LiveData<ResponseState<OrderDto>> = _order

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            repository.getItemsIdsProducts(id).asResponseState().collect {
                _itemID.value = it
            }
        }
    }

    fun createOrders(productId: Int) {
        val lineItemList = mutableListOf<LineItem>()
        val lineItem = LineItem(0, productId, 1)
        lineItemList.add(lineItem)
        val order = OrderDto(0, lineItemList)
        viewModelScope.launch {
            repository.createOrders(order).asResponseState().collect {
                _order.postValue(it)
                Log.e(ContentValues.TAG, "createOrders: $it")
            }
        }
    }

    fun insert(lineItemEntity: LineItemEntity) {
        viewModelScope.launch {
            repository.insert(lineItemEntity)
        }
    }
}