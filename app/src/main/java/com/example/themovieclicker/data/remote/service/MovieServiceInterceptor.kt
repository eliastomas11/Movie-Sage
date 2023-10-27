package com.example.themovieclicker.data.remote.service

import android.content.Context
import com.example.themovieclicker.R
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class MovieServiceInterceptor @Inject constructor(context: Context) : Interceptor {

    private val apiKey = context.getString(R.string.api_key)
    private val TAG = "Network Interceptor"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization","Bearer $apiKey")
            .addHeader("accept", "application/json")
            .build()

        Timber.d( "Request: ${request.method()} ${request.url()}")

        val response = chain.proceed(request)

        Timber.d("${response.code()} ${response.message()} ${response.body()}")

        return response
    }
}