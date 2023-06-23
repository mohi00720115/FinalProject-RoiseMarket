package com.example.final_test_01.data.model.ui

data class MyCustomerItem(
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    companion object {
        val empty = MyCustomerItem(
            "Ali", "Reza", ""
        )
    }
}
