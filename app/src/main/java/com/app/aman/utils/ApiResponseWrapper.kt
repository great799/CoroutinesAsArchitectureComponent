package com.app.aman.utils

sealed class ApiResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ApiResponseWrapper<T>()
    data class GenericError(val code: Int?, val errorMessage: String?) :
        ApiResponseWrapper<Nothing>()

    object NetworkError : ApiResponseWrapper<Nothing>()
    data class Loading(val isLoading: Boolean) : ApiResponseWrapper<Nothing>()
}