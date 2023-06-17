package com.example.final_test_01.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.ResponseState.Loading
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val email = MutableStateFlow("")

    private val _customerId = MutableStateFlow<ResponseState<CustomerDto>>(Loading)
    val customerId = _customerId

    fun createCustomerObject() {
        val customer = CustomerDto(email.value, firstName.value, lastName.value)
        createCustomer(customer)
    }

    private fun createCustomer(customer: CustomerDto) {
        viewModelScope.launch {
            repository.createCustomer(customer).asResponseState()
        }
    }
}