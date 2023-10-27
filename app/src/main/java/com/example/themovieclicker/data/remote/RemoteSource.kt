package com.example.themovieclicker.data.remote

import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    fun getMovies() : Flow<List<MovieDto>>
}