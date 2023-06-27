package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class ShippingLine(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("meta_data")
    val metaData: List<Any?>?,
    @SerializedName("method_id")
    val methodId: String?,
    @SerializedName("method_title")
    val methodTitle: String?,
    @SerializedName("taxes")
    val taxes: List<Any?>?,
    @SerializedName("total")
    val total: String?,
    @SerializedName("total_tax")
    val totalTax: String?
)