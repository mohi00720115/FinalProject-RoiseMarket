package com.example.final_test_01.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.order.Billing
import com.example.final_test_01.data.model.dto.order.LineItem
import com.example.final_test_01.data.model.dto.order.OrderDto
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
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
//    private val _cartList = MutableStateFlow<ResponseState<List<ProductsItemsDto>>>(Loading)
//    val cartList: StateFlow<ResponseState<List<ProductsItemsDto>>> = _cartList

//    private val _product = MutableStateFlow<List<LineItemEntity>>(emptyList())
//    val product: StateFlow<List<LineItemEntity>> = _product

    private val _updateOrderCart = MutableStateFlow<ResponseState<OrderDto>>(Loading)
    val updateOrderCart: StateFlow<ResponseState<OrderDto>> = _updateOrderCart

    private val _orderByIdCart = MutableStateFlow<ResponseState<OrderDto>>(Loading)
    val orderByIdCart: StateFlow<ResponseState<OrderDto>> = _orderByIdCart

//    private val productList = mutableListOf<ProductsItemsDto>()

//    fun getItemsIdsProducts(id: Int) {
//        viewModelScope.launch {
//            repository.getItemsIdsProducts(id).asResponseState().collect {
//                _cartList.value = it
//                Log.e(TAG, "_cartList.value: $it")
//            }
//        }
//    }

//    /**
//     * تک محصول رو می گیره و اضافه می کنه به یک لیست
//     */
//    fun addItemToList(productItem: List<ProductsItemsDto>): List<ProductsItemsDto> {
//        productList += productItem
//        return productList
//    }

    fun putUpdateOrder(packageId: Int, quantity: Int, productId: Int) {
        val itemList = mutableListOf<LineItem>()
        val lineItem = LineItem(packageId = packageId, quantity = quantity, productId = productId)
        itemList.add(lineItem)
        val billing = Billing(email = CUSTOMER_EMAIL)
        val order = OrderDto(billing = billing, lineItems = itemList, id = ORDERS_ID)
        viewModelScope.launch {
            repository.putUpdateOrder(ORDERS_ID, order).asResponseState().collect {
                _orderByIdCart.value = it
            }
        }
    }

    fun getOrderById() {
        viewModelScope.launch {
            repository.getOrderById(ORDERS_ID).asResponseState().collect {
                _orderByIdCart.value = it
            }
        }
    }

}