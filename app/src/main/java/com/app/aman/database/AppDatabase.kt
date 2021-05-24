package com.app.aman.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.aman.network.model.StockDetail

@Database(entities = [StockDetail::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}