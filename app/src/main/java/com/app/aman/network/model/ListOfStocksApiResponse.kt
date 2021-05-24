package com.app.aman.network.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class ListOfStocksApiResponse(
    @field:Json(name = "success") val success: Boolean,
    @field:Json(name = "data") val data: List<StockDetail>
)

@Entity
@Parcelize
data class StockDetail(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "sid") @field:Json(name = "sid") val sid: String,
    @ColumnInfo(name = "price") @field:Json(name = "price") val price: Double,
    @ColumnInfo(name = "close") @field:Json(name = "close") val close: Double,
    @ColumnInfo(name = "change") @field:Json(name = "change") val change: Double,
    @ColumnInfo(name = "high") @field:Json(name = "high") val high: Double,
    @ColumnInfo(name = "low") @field:Json(name = "low") val low: Double,
    @ColumnInfo(name = "volume") @field:Json(name = "volume") val volume: Long,
    @ColumnInfo(name = "date") @field:Json(name = "date") val date: String
) : Parcelable