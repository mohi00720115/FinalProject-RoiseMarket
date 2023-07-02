package com.example.final_test_01.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _updateOrder = MutableStateFlow<ResponseState<OrderDto>>(Loading)
    val updateOrder: StateFlow<ResponseState<OrderDto>> = _updateOrder

    private val _orderById = MutableStateFlow<ResponseState<OrderDto>>(Loading)
    val orderById: StateFlow<ResponseState<OrderDto>> = _orderById

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            repository.getItemsIdsProducts(id).asResponseState().collect {
                _itemID.value = it
            }
        }
    }

    fun putUpdateOrder(id: Int, customerOrder: OrderDto) {
        viewModelScope.launch {
            repository.putUpdateOrder(id, customerOrder).asResponseState().collect {
                _updateOrder.value = it
            }
        }
    }

}