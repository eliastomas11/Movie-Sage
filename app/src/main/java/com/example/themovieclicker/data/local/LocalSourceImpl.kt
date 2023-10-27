package com.example.themovieclicker.data.local

import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.core.mappers.toLocalCache
import com.example.themovieclicker.core.mappers.toLocalFavorite
import com.example.themovieclicker.data.local.service.MovieCacheDao
import com.example.themovieclicker.data.local.service.FavoriteMovieDao
import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(
    private val movieCacheDao: MovieCacheDao,
    private val favoriteMovieDao: FavoriteMovieDao,
) : LocalSource {
    override fun getMovies(): Flow<List<MovieDto>> {
        return movieCacheDao.getMovies().map {
            it.map { movieCacheEntity ->  movieCacheEntity.toDto() } }
    }

    override fun getMovieById(id: Int): MovieDto {
        return movieCacheDao.getMovieById(id).toDto()
    }

    override fun saveMovieToFavorites(movie: MovieDto): Int {
        return favoriteMovieDao.insertMovie(movie.toLocalFavorite())
    }

    override fun saveMovies(movies: List<MovieDto>) {
        movieCacheDao.insertMovie(movies.map { it.toLocalCache() })
    }
}