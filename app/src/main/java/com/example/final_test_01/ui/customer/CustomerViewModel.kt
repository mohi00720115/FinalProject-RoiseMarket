package com.example.final_test_01.ui.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _customer = MutableLiveData<CustomerDto>()
    val customer: LiveData<CustomerDto> = _customer

    fun createObjectDto(firstName: String, lastName: String, email: String) {
        createCustomerByEmail(CustomerDto(firstName, lastName, email))
    }

    private fun createCustomerByEmail(dto: CustomerDto) {
        viewModelScope.launch {
            repository.createCustomer(dto).collect {
                _customer.postValue(it)
                Log.e(TAG, "createCustomerByEmail: $it")
            }
        }
    }

}