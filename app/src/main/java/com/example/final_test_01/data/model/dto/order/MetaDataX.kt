package com.example.final_test_01.data.model.dto.order


import com.google.gson.annotations.SerializedName

data class MetaDataX(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("value")
    val value: String?
)