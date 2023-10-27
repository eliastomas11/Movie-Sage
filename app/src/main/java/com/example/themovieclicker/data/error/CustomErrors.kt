package com.example.themovieclicker.data.error

import retrofit2.Response

sealed class CustomErrors {

    data object NoInternetConnection : CustomErrors()

    data object UnknownError : CustomErrors()

    data object ServerError : CustomErrors()

    data class  NetworkError(val msg:String) : CustomErrors()

    sealed class ApiError(val errorCode: Int) {


    }
}

fun CustomErrors.ApiError.toCustomError(): CustomErrors {
    when (this.errorCode) {
        404 -> return CustomErrors.NetworkError("Resource not found")
    }
}

