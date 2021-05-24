package com.app.aman.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.aman.network.model.StockDetail

@Dao
interface StockDao {
    @Query("SELECT * FROM stockdetail")
    fun getAll(): LiveData<List<StockDetail>>

    @Insert
    suspend fun insertListOfStocks(listOfStocks: List<StockDetail>)
}