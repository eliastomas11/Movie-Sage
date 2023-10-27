package com.example.themovieclicker.domain

import com.example.themovieclicker.domain.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(page: Int): Flow<List<MovieModel>>
    fun saveMovieToFavorites(movie: MovieModel) : Int
}
