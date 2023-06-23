package com.example.final_test_01.mapper

import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.ui.MyCustomerItem

fun customerItemToCustomerDto(customerItem: MyCustomerItem): CustomerDto {
    return CustomerDto(
        email = customerItem.email,
        firstName = customerItem.firstName,
        lastName = customerItem.lastName
    )
}