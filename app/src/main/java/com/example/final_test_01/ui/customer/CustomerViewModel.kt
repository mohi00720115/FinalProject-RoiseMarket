package com.example.final_test_01.ui.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val email = MutableLiveData("")

    private val _customerId = MutableLiveData<CustomerDto>()
    val customerId = _customerId

    fun createCustomerObject() {
        val customer = CustomerDto(email.value!!, firstName.value!!, lastName.value!!)
        createCustomer(customer)
    }

    private fun createCustomer(customer: CustomerDto) {
        viewModelScope.launch {
            repository.createCustomer(customer).asResponseState()
        }
    }
}