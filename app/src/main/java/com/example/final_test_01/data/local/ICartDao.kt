package com.example.final_test_01.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ICartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lineItemEntity: LineItemEntity)

    @Update
    suspend fun update(lineItemEntity: LineItemEntity)

    @Delete
    suspend fun delete(lineItemEntity: LineItemEntity)

    @Query("SELECT * FROM line_item_table")
    fun getAllProduct(): List<LineItemEntity>

}