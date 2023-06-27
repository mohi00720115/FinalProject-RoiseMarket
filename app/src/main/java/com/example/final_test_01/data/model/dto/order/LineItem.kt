package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class LineItem(
    @SerializedName("id")
    val id: Int?,
//    @SerializedName("meta_data")
//    val metaData: List<MetaDataX>?,
//    @SerializedName("name")
//    val name: String?,
//    @SerializedName("price")
//    val price: Int?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("quantity")
    val quantity: Int?,
//    @SerializedName("sku")
//    val sku: String?,
//    @SerializedName("subtotal")
//    val subtotal: String?,
//    @SerializedName("subtotal_tax")
//    val subtotalTax: String?,
//    @SerializedName("tax_class")
//    val taxClass: String?,
//    @SerializedName("taxes")
//    val taxes: List<Taxe>?,
//    @SerializedName("total")
//    val total: String?,
//    @SerializedName("total_tax")
//    val totalTax: String?,
//    @SerializedName("variation_id")
//    val variationId: Int?
)