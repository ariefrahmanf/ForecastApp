package com.mobile.weatherapp.data.utils

sealed class ApiResponse<out R> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error(var errorMessage: String): ApiResponse<Nothing>()
    object Empty: ApiResponse<Nothing>()
}
