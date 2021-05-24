package com.app.aman.repository

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.app.aman.AppApplication
import com.app.aman.base.BaseRepository
import com.app.aman.database.AppDatabase
import com.app.aman.network.ServiceApi
import com.app.aman.network.model.ListOfStocksApiResponse
import com.app.aman.utils.ApiResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository : BaseRepository() {

    @Inject
    lateinit var serviceApi: ServiceApi

//    @Inject
//    lateinit var database: AppDatabase

    init {
        AppApplication.getAppComponent().inject(this)
    }

    suspend fun fetchListOfStocks(sids: String): ApiResponseWrapper<ListOfStocksApiResponse> {
        return safeApiCall(Dispatchers.IO) {
            serviceApi.fetchListOfStocks(sids)
        }
    }

//    fun fetchListOfStocksFromDBAndRefresh(sids: String) =
//        liveData<ApiResponseWrapper<ListOfStocksApiResponse>>(Dispatchers.IO) {
//            emitSource(database.stockDao().getAll().map {
//                ApiResponseWrapper.Success(ListOfStocksApiResponse(true, it))
//            })
//            delay(5000)
//            val response = safeApiCall(Dispatchers.IO) {
//                serviceApi.fetchListOfStocks(sids)
//            }
//            when (response) {
//                is ApiResponseWrapper.Success -> {
//                    database.stockDao().insertListOfStocks(response.value.data)
//                }
//            }
//        }
}