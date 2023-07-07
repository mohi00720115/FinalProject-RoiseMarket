package com.example.final_test_01.data.model.dto.review


import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("date_created")
    val dateCreated: String? = "",
//    @SerializedName("date_created_gmt")
//    val dateCreatedGmt: String? = "",
    @SerializedName("id")
    val reviewId: Int?=0,
//    @SerializedName("_links")
//    val links: Links?,
    @SerializedName("product_id")
    val productId: Int? = 0,
    @SerializedName("rating")
    val rating: Int? = 0,
    @SerializedName("review")
    var review: String? = "",
    @SerializedName("reviewer")
    var reviewer: String? = "",
//    @SerializedName("reviewer_avatar_urls")
//    val reviewerAvatarUrls: ReviewerAvatarUrls?,
    @SerializedName("reviewer_email")
    var reviewerEmail: String? = "",
//    @SerializedName("status")
//    val status: String? = "",
//    @SerializedName("verified")
//    val verified: Boolean?
)