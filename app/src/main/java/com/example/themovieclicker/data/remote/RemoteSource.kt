package com.example.themovieclicker.data.remote

import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.model.ResourceResponse
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    suspend fun getMovies(page: Int): List<MovieDto>
}