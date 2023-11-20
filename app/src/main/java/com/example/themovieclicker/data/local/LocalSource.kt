package com.example.themovieclicker.data.local

import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

interface LocalSource {

    fun getMovies(): Flow<List<MovieDto>>

    fun getFavoritesMovies(): Flow<List<MovieDto>>

    suspend fun getMovieById(id: Int): MovieDto

    suspend fun saveMovieToFavorites(movie: MovieDto): Long

    suspend fun saveMovies(movies: List<MovieDto>)
    suspend fun isEmpty(): Boolean

    suspend fun deleteMovieFromFavorite(movie: MovieDto)
    suspend fun refresh()

    suspend fun isMovieFavorite(movieId: Int): Int
}