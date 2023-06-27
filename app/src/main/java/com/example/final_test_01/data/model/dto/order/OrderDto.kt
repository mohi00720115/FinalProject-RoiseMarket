package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class OrderDto(
//    @SerializedName("billing")
//    val billing: Billing?,
//    @SerializedName("cart_hash")
//    val cartHash: String?,
//    @SerializedName("cart_tax")
//    val cartTax: String?,
//    @SerializedName("coupon_lines")
//    val couponLines: List<Any>?,
//    @SerializedName("created_via")
//    val createdVia: String?,
//    @SerializedName("currency")
//    val currency: String?,
//    @SerializedName("customer_id")
//    val customerId: Int?,
//    @SerializedName("customer_ip_address")
//    val customerIpAddress: String?,
//    @SerializedName("customer_note")
//    val customerNote: String?,
//    @SerializedName("customer_user_agent")
//    val customerUserAgent: String?,
//    @SerializedName("date_completed")
//    val dateCompleted: Any?,
//    @SerializedName("date_completed_gmt")
//    val dateCompletedGmt: Any?,
//    @SerializedName("date_created")
//    val dateCreated: String?,
//    @SerializedName("date_created_gmt")
//    val dateCreatedGmt: String?,
//    @SerializedName("date_modified")
//    val dateModified: String?,
//    @SerializedName("date_modified_gmt")
//    val dateModifiedGmt: String?,
//    @SerializedName("date_paid")
//    val datePaid: String?,
//    @SerializedName("date_paid_gmt")
//    val datePaidGmt: String?,
//    @SerializedName("discount_tax")
//    val discountTax: String?,
//    @SerializedName("discount_total")
//    val discountTotal: String?,
//    @SerializedName("fee_lines")
//    val feeLines: List<Any>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("line_items")
    val lineItems: List<LineItem>,
//    @SerializedName("_links")
//    val links: Links?,
//    @SerializedName("meta_data")
//    val metaData: List<MetaDataX>?,
//    @SerializedName("number")
//    val number: String?,
//    @SerializedName("order_key")
//    val orderKey: String?,
//    @SerializedName("parent_id")
//    val parentId: Int?,
//    @SerializedName("payment_method")
//    val paymentMethod: String?,
//    @SerializedName("payment_method_title")
//    val paymentMethodTitle: String?,
//    @SerializedName("prices_include_tax")
//    val pricesIncludeTax: Boolean?,
//    @SerializedName("refunds")
//    val refunds: List<Any>?,
//    @SerializedName("shipping")
//    val shipping: Shipping?,
//    @SerializedName("shipping_lines")
//    val shippingLines: List<ShippingLine>?,
//    @SerializedName("shipping_tax")
//    val shippingTax: String?,
//    @SerializedName("shipping_total")
//    val shippingTotal: String?,
//    @SerializedName("status")
//    val status: String?,
//    @SerializedName("tax_lines")
//    val taxLines: List<TaxLine>?,
//    @SerializedName("total")
//    val total: String?,
//    @SerializedName("total_tax")
//    val totalTax: String?,
//    @SerializedName("transaction_id")
//    val transactionId: String?,
//    @SerializedName("version")
//    val version: String?
)