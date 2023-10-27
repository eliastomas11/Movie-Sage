package com.example.themovieclicker.data.remote.service

import com.example.themovieclicker.data.remote.model.MovieRemoteResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular?language=en-US&sort_by=popularity.desc")
    suspend fun getMoviesByPopularity(@Query("page") page: Int) : Response<MovieRemoteResponse>
}