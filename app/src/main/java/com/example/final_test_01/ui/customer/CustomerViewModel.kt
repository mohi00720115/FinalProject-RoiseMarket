package com.example.final_test_01.ui.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.Billing
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _customer = MutableLiveData<ResponseState<CustomerDto>>()
    val customer: LiveData<ResponseState<CustomerDto>> = _customer

    private val _getEmailCustomer = MutableLiveData<ResponseState<List<CustomerDto>>>()
    val getEmailCustomer: LiveData<ResponseState<List<CustomerDto>>> = _getEmailCustomer

    private val _searchByEmail = MutableLiveData<ResponseState<List<OrderDto>>>()
    val searchByEmail: LiveData<ResponseState<List<OrderDto>>> = _searchByEmail

    private val _order = MutableLiveData<ResponseState<OrderDto>>()
    val order: LiveData<ResponseState<OrderDto>> = _order

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val email = MutableLiveData("")

    fun createCustomer() {
        createCustomerByEmail(CustomerDto(firstName.value!!, lastName.value!!, email.value!!))
    }

    fun getCustomersByEmail() {
        viewModelScope.launch {
            repository.getCustomersByEmail(email.value!!).asResponseState().collect {
                _getEmailCustomer.postValue(it)
            }
        }
    }

    private fun createCustomerByEmail(dto: CustomerDto) {
        viewModelScope.launch {
            repository.createCustomer(dto).asResponseState().collect {
                _customer.postValue(it)
                Log.e(TAG, "createCustomerByEmail: $it")
            }
        }
    }

    fun getOrdersByEmail() {
        viewModelScope.launch {
            repository.getOrdersByEmail(email.value!!).asResponseState().collect {
                _searchByEmail.postValue(it)
            }
        }
    }

    fun createOrders() {
        val billing = Billing(email = email.value!!)
        val order = OrderDto(billing)
        viewModelScope.launch {
            repository.createOrders(order).asResponseState().collect {
                _order.postValue(it)
                Log.e(TAG, "createOrders: $it")
            }
        }
    }

    /**
     * چک کردن خالی نبودن ورودی های ثبت نام
     */
    fun checkCustomerEntries() =
        !(firstName.value!!.isEmpty() || lastName.value!!.isEmpty() || email.value!!.isEmpty())

}