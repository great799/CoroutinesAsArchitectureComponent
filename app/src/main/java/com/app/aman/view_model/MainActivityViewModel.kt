package com.app.aman.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.aman.base.BaseViewModel
import com.app.aman.network.model.ListOfStocksApiResponse
import com.app.aman.repository.MainRepository
import com.app.aman.utils.ApiResponseWrapper
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    val apiResponseLiveData: MutableLiveData<ListOfStocksApiResponse> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val networkErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val apiErrorLiveData: MutableLiveData<ApiResponseWrapper.GenericError> = MutableLiveData()

//    fun fetchAndRefreshStocksLiveData(sids: List<String>) =
//        mainRepository.fetchListOfStocksFromDBAndRefresh(
//            sids.joinToString(
//                ","
//            )
//        )

    fun getListOfStocks(sids: List<String>) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val response = mainRepository.fetchListOfStocks(
                sids.joinToString(
                    ","
                )
            )
            when (response) {
                is ApiResponseWrapper.Success -> {
                    loadingLiveData.postValue(false)
                    apiResponseLiveData.postValue(response.value)
                }
                is ApiResponseWrapper.NetworkError -> {
                    loadingLiveData.postValue(false)
                    networkErrorLiveData.postValue("Check your Internet Connection")
                }
                is ApiResponseWrapper.GenericError -> {
                    loadingLiveData.postValue(false)
                    apiErrorLiveData.postValue(response)
                }
            }
        }
    }
}