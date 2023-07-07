package com.example.final_test_01.data.model.dto.coupon


import com.google.gson.annotations.SerializedName

data class CouponDtoItem(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("date_created_gmt")
    val dateCreatedGmt: String?,
    @SerializedName("date_expires")
    val dateExpires: Any?,
    @SerializedName("date_expires_gmt")
    val dateExpiresGmt: Any?,
    @SerializedName("date_modified")
    val dateModified: String?,
    @SerializedName("date_modified_gmt")
    val dateModifiedGmt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discount_type")
    val discountType: String?,
    @SerializedName("email_restrictions")
    val emailRestrictions: List<Any?>?,
    @SerializedName("exclude_sale_items")
    val excludeSaleItems: Boolean?,
    @SerializedName("excluded_product_categories")
    val excludedProductCategories: List<Any?>?,
    @SerializedName("excluded_product_ids")
    val excludedProductIds: List<Any?>?,
    @SerializedName("free_shipping")
    val freeShipping: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("individual_use")
    val individualUse: Boolean?,
    @SerializedName("limit_usage_to_x_items")
    val limitUsageToXItems: Any?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("maximum_amount")
    val maximumAmount: String?,
    @SerializedName("meta_data")
    val metaData: List<Any?>?,
    @SerializedName("minimum_amount")
    val minimumAmount: String?,
    @SerializedName("product_categories")
    val productCategories: List<Any?>?,
    @SerializedName("product_ids")
    val productIds: List<Any?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("usage_count")
    val usageCount: Int?,
    @SerializedName("usage_limit")
    val usageLimit: Any?,
    @SerializedName("usage_limit_per_user")
    val usageLimitPerUser: Any?,
    @SerializedName("used_by")
    val usedBy: List<String?>?
)