package com.example.themovieclicker.data.remote

import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.model.ResourceResponse
import com.example.themovieclicker.data.remote.service.MovieApiService
import timber.log.Timber
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(private val apiService: MovieApiService) : RemoteSource {
    override suspend fun getMovies(page: Int): List<MovieDto> {
        val moviesResponse = apiService.getMoviesByPopularity(page)
        if (moviesResponse.isSuccessful) {
            moviesResponse.body()?.let { moviesResponse ->
                return@let moviesResponse.movieResults.map { it.toDto() }
            }
        }
        Timber.e("Error: ${moviesResponse.code()}")
        throw CustomException.ApiException(moviesResponse.code())

    }
}