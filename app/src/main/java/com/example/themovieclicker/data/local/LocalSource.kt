package com.example.themovieclicker.data.local

import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

interface LocalSource {

    fun getMovies(): Flow<List<MovieDto>>

    fun getMovieById(id: Int): MovieDto

    fun saveMovieToFavorites(movie: MovieDto): Int

    fun saveMovies(movies: List<MovieDto>)
}