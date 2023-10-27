package com.example.themovieclicker.data.remote.service

import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.data.model.MovieDto
import retrofit2.Response
import javax.inject.Inject

class ApiServiceManager @Inject constructor(private val apiService: MovieApiService) {

    suspend fun getMoviesByPopularity(page: Int): Response<MovieDto> {
        val moviesResponse = apiService.getMoviesByPopularity(page)
        if(moviesResponse.isSuccessful){
            moviesResponse.body()?.let { moviesResponse ->
                return@let moviesResponse.movieResults.map { it.toDto() }
            }
        }
         return ApiError(moviesResponse.code())

    }
}