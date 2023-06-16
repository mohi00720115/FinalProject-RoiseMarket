package com.example.final_test_01.data.model.dto.product_category


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("collection")
    val collection: List<Collection?>?,
    @SerializedName("self")
    val self: List<Self?>?,
    @SerializedName("up")
    val up: List<Up?>?
)