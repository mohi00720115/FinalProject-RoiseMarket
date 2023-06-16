package com.example.final_test_01.data.model.dto.customer

import com.google.gson.annotations.SerializedName

data class CustomerDto(
//    val id: Int = 25,
    val email: String,
//    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
//    @SerializedName("avatar_url")
//    val avatar_url: String,
//    val role: String
)