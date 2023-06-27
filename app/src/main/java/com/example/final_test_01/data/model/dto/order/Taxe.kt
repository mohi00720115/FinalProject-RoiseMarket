package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class Taxe(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("subtotal")
    val subtotal: String?,
    @SerializedName("total")
    val total: String?
)