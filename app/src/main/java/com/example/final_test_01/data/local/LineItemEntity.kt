package com.example.final_test_01.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "line_item_table", primaryKeys = ["product_id"])
data class LineItemEntity(
    @ColumnInfo("product_id")
    val productId: Int,
    val quantity: Int,
    val id: Int,
    )