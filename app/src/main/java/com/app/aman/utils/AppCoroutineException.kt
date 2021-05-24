package com.app.aman.utils

sealed class AppCoroutineException : Exception() {
    object NetworkError : AppCoroutineException()
    data class ApiError(val code: Int?, val errorMessage: String?) : AppCoroutineException()
}