package com.example.final_test_01.ui.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.ResponseState.Loading
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _customer = MutableStateFlow<CustomerDto>(CustomerDto("", "", ""))
    val customer: StateFlow<CustomerDto> = _customer

    fun createCustomerByEmail(
//        email: String,
        dto: CustomerDto
    ) {
        viewModelScope.launch {
            repository.createCustomer(dto).collect {
                _customer.value = it
                Log.e(TAG, "createCustomerByEmail: $it")
            }

        }
    }

}