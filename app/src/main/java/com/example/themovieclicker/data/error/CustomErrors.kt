package com.example.themovieclicker.data.error

import retrofit2.Response

sealed class CustomErrors(val message: String? = null) {

    data object NoInternetConnection :
        CustomErrors(message = "Internet connection is needed to show the movies")

    data object UnknownError :
        CustomErrors(message = "Something went wrong please try again later or try refreshing the page")

    data object ServerError :
        CustomErrors(message = "Something went wrong please try again later or try refreshing the page")

    data object FailToSaveError :
        CustomErrors(message = "Something went wrong when trying to save the movie,please try again later")

    sealed class ApiError(val code: Int, val msg: String) : CustomErrors() {

        data object NotFound : ApiError(404, "Not Found")

        data object InternalServerError : ApiError(500, "Internal Server Error")

        data object Unauthorized : ApiError(401, "Unauthorized")

        data object Forbidden : ApiError(403, "Forbidden")

        data object BadRequest : ApiError(400, "Bad Request")

    }

    companion object {

        fun mapToApiError(code: Int, msg: String?): CustomErrors.ApiError {
            return when (code) {
                404 -> CustomErrors.ApiError.NotFound
                500 -> CustomErrors.ApiError.InternalServerError
                401 -> CustomErrors.ApiError.Unauthorized
                403 -> CustomErrors.ApiError.Forbidden
                400 -> CustomErrors.ApiError.BadRequest
                else -> CustomErrors.ApiError.InternalServerError
            }

        }
    }
}






