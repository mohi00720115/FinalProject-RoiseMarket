package com.example.final_test_01.data.model.dto.product


import com.google.gson.annotations.SerializedName

data class ProductsItemsDto(
    @SerializedName("attributes")
    val attributes: List<Any?>?,
    @SerializedName("average_rating")
    val averageRating: String?,
    @SerializedName("backordered")
    val backordered: Boolean?,
    @SerializedName("backorders")
    val backorders: String?,
    @SerializedName("backorders_allowed")
    val backordersAllowed: Boolean?,
    @SerializedName("button_text")
    val buttonText: String?,
    @SerializedName("catalog_visibility")
    val catalogVisibility: String?,
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("cross_sell_ids")
    val crossSellIds: List<Any?>?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("date_created_gmt")
    val dateCreatedGmt: String?,
    @SerializedName("date_modified")
    val dateModified: String?,
    @SerializedName("date_modified_gmt")
    val dateModifiedGmt: String?,
    @SerializedName("date_on_sale_from")
    val dateOnSaleFrom: Any?,
    @SerializedName("date_on_sale_from_gmt")
    val dateOnSaleFromGmt: Any?,
    @SerializedName("date_on_sale_to")
    val dateOnSaleTo: Any?,
    @SerializedName("date_on_sale_to_gmt")
    val dateOnSaleToGmt: Any?,
    @SerializedName("default_attributes")
    val defaultAttributes: List<Any?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("dimensions")
    val dimensions: Dimensions?,
    @SerializedName("download_expiry")
    val downloadExpiry: Int?,
    @SerializedName("download_limit")
    val downloadLimit: Int?,
    @SerializedName("downloadable")
    val downloadable: Boolean?,
    @SerializedName("downloads")
    val downloads: List<Any?>?,
    @SerializedName("external_url")
    val externalUrl: String?,
    @SerializedName("featured")
    val featured: Boolean?,
    @SerializedName("grouped_products")
    val groupedProducts: List<Any?>?,
    @SerializedName("has_options")
    val hasOptions: Boolean?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("low_stock_amount")
    val lowStockAmount: Any?,
    @SerializedName("manage_stock")
    val manageStock: Boolean?,
    @SerializedName("menu_order")
    val menuOrder: Int?,
    @SerializedName("meta_data")
    val metaData: List<Any?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("on_sale")
    val onSale: Boolean?,
    @SerializedName("parent_id")
    val parentId: Int?,
    @SerializedName("permalink")
    val permalink: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("price_html")
    val priceHtml: String?,
    @SerializedName("purchasable")
    val purchasable: Boolean?,
    @SerializedName("purchase_note")
    val purchaseNote: String?,
    @SerializedName("rating_count")
    val ratingCount: Int?,
    @SerializedName("regular_price")
    val regularPrice: String?,
    @SerializedName("related_ids")
    val relatedIds: List<Int?>?,
    @SerializedName("reviews_allowed")
    val reviewsAllowed: Boolean?,
    @SerializedName("sale_price")
    val salePrice: String?,
    @SerializedName("shipping_class")
    val shippingClass: String?,
    @SerializedName("shipping_class_id")
    val shippingClassId: Int?,
    @SerializedName("shipping_required")
    val shippingRequired: Boolean?,
    @SerializedName("shipping_taxable")
    val shippingTaxable: Boolean?,
    @SerializedName("short_description")
    val shortDescription: String?,
    @SerializedName("sku")
    val sku: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("sold_individually")
    val soldIndividually: Boolean?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("stock_quantity")
    val stockQuantity: Any?,
    @SerializedName("stock_status")
    val stockStatus: String?,
    @SerializedName("tags")
    val tags: List<Tag?>?,
    @SerializedName("tax_class")
    val taxClass: String?,
    @SerializedName("tax_status")
    val taxStatus: String?,
    @SerializedName("total_sales")
    val totalSales: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("upsell_ids")
    val upsellIds: List<Any?>?,
    @SerializedName("variations")
    val variations: List<Any?>?,
    @SerializedName("virtual")
    val virtual: Boolean?,
    @SerializedName("weight")
    val weight: String?
)