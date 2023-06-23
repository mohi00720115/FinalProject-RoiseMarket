package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("address_1")
    val address1: String?,
    @SerializedName("address_2")
    val address2: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("state")
    val state: String?
)