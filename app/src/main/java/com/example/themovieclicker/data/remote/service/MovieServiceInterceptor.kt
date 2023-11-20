package com.example.themovieclicker.data.remote.service

import android.content.Context
import com.example.themovieclicker.R
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MovieServiceInterceptor @Inject constructor(context: Context) : Interceptor {

    private val apiKey = context.getString(R.string.api_key)

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val request = chain.request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .url(url)
            .build()

        return chain.proceed(request)
    }
}