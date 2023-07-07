package com.example.final_test_01.ui.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.order.Billing
import com.example.final_test_01.data.model.dto.order.LineItem
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.Const.CUSTOMER_EMAIL
import com.example.final_test_01.util.Const.ORDERS_ID
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

    private val _reviewList = MutableStateFlow<ResponseState<List<ReviewDto>>>(Loading)
    val reviewList: StateFlow<ResponseState<List<ReviewDto>>> = _reviewList

    private val _review = MutableStateFlow<ResponseState<ReviewDto>>(Loading)
    val review: StateFlow<ResponseState<ReviewDto>> = _review

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            repository.getItemsIdsProducts(id).asResponseState().collect {
                _itemID.value = it
            }
        }
    }

    fun getOrderById() {
        viewModelScope.launch {
            repository.getOrderById(ORDERS_ID).asResponseState().collect {
                _orderById.value = it
                Log.e(TAG, "ORDERS_ID: $ORDERS_ID")
            }
        }
    }

    fun getProductReviewList(id: Int) {
        viewModelScope.launch {
            repository.getProductReviewList(id).asResponseState().collect {
                _reviewList.value = it
            }
        }
    }

    /**
     * میاد بر اساس ایمیل کاربر و آی دی سفارش، سفارشش رو آپدیت می کنه
     */
    fun putUpdateOrder(productId: Int) {
        val itemList = mutableListOf<LineItem>()
        val lineItem = LineItem(productId = productId)
        itemList.add(lineItem)
        val billing = Billing(email = CUSTOMER_EMAIL)
        val order = OrderDto(billing = billing, id = ORDERS_ID, lineItems = itemList)
        viewModelScope.launch {
            repository.putUpdateOrder(productId, order).asResponseState().collect {
                _updateOrder.value = it
            }
        }
    }

    fun deleteReview(id: Int) {
        viewModelScope.launch {
            repository.deleteReview(id).asResponseState().collect {
                _review.value = it
            }
        }
    }

}