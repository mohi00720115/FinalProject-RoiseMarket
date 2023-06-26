package com.example.final_test_01.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LineItemEntity::class], version = 1)
abstract class OrderDataBase : RoomDatabase() {
    abstract fun cartDao(): ICartDao
}