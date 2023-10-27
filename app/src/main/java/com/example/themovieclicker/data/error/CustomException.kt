package com.example.themovieclicker.data.error

sealed class CustomException(message: String? = null) : Exception(message) {
    data class ApiException(val code: Int, val msg: String? = null) :
        CustomException(msg)

    data object NoNetworkConnection : CustomException()
    data object ServerError : CustomException()

    fun mapToCustomError(): CustomErrors {
        return when (this) {
            is NoNetworkConnection -> CustomErrors.NoInternetConnection
            is ServerError -> CustomErrors.ServerError
            is ApiException -> CustomErrors.mapToApiError(this.code,this.msg)
        }
    }
}
