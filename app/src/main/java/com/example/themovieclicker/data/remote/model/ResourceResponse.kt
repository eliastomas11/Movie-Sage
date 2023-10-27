package com.example.themovieclicker.data.remote.model

import java.lang.Exception

sealed class ResourceResponse<out T>(val body: T? = null,val e: Exception? = null) {

    data class Success<T>(val data: T) : ResourceResponse<T>(body = data)
    data class Error(val exception: Exception) : ResourceResponse<Nothing>(e = exception)
}