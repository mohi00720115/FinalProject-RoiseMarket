package com.example.final_test_01.ui.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.Billing
import com.example.final_test_01.data.model.dto.order.OrderDto
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
class CustomerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _order = MutableStateFlow<ResponseState<OrderDto>>(Loading)
    val order: StateFlow<ResponseState<OrderDto>> = _order

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val email = MutableLiveData("")

    private val _login = MutableStateFlow<ResponseState<CustomerDto>>(Loading)
    val login: StateFlow<ResponseState<CustomerDto>> = _login

    /**
     * چک کردن تمام شروط ثبت نام و ایجاد کاربر و ساخت و نمایش سفارشاتش
     */
    fun checkLogin() {
        viewModelScope.launch {
            val billing = Billing(email = email.value!!)
            repository.checkLogin(
                CustomerDto(firstName.value!!, lastName.value!!, email.value!!),
                OrderDto(billing)
            ).asResponseState().collect {
                _login.value = it
            }
        }
    }

    /**
     * چک کردن خالی نبودن ورودی های ثبت نام
     */
    fun checkCustomerEntries() =
        !(firstName.value!!.isEmpty() || lastName.value!!.isEmpty() || email.value!!.isEmpty())

}