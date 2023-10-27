package com.example.themovieclicker.data.error

import retrofit2.Response

sealed class CustomErrors {

    data object NoInternetConnection : CustomErrors()

    data object UnknownError : CustomErrors()

    data object ServerError : CustomErrors()

    data class NetworkError(val msg: String) : CustomErrors()

    sealed class ApiError(val code: Int, val msg: String) : CustomErrors() {

        data object NotFound : ApiError(404, "Not Found")

        data object InternalServerError : ApiError(500, "Internal Server Error")

    }
    companion object {
        fun mapToApiError(code: Int, msg: String?): CustomErrors.ApiError {
            return when (code) {
                404 -> CustomErrors.ApiError.NotFound
                else -> CustomErrors.ApiError.InternalServerError
            }

        }
    }
}






