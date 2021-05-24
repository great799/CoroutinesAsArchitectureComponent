package com.app.aman.network

import com.app.aman.network.model.ListOfStocksApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("stocks/quotes")
    suspend fun fetchListOfStocks(@Query("sids") sids: String): ListOfStocksApiResponse
}