package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class TaxLine(
    @SerializedName("compound")
    val compound: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("meta_data")
    val metaData: List<Any?>?,
    @SerializedName("rate_code")
    val rateCode: String?,
    @SerializedName("rate_id")
    val rateId: Int?,
    @SerializedName("shipping_tax_total")
    val shippingTaxTotal: String?,
    @SerializedName("tax_total")
    val taxTotal: String?
)