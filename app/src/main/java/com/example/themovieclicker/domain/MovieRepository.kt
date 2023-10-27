package com.example.themovieclicker.domain

import android.graphics.Movie
import com.example.themovieclicker.domain.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(page: Int): Flow<List<MovieModel>>
    suspend fun saveMovieToFavorites(movie: MovieModel): Long

    suspend fun getMovieById(id: Int): MovieModel

    fun getFavoritesMovies(): Flow<List<MovieModel>>

    suspend fun deleteFromFavorite(movie: MovieModel)


}
