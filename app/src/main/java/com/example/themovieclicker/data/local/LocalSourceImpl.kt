package com.example.themovieclicker.data.local

import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.core.mappers.toLocalCache
import com.example.themovieclicker.core.mappers.toLocalFavorite
import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import com.example.themovieclicker.data.local.service.MovieCacheDao
import com.example.themovieclicker.data.local.service.FavoriteMovieDao
import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(
    private val movieCacheDao: MovieCacheDao,
    private val favoriteMovieDao: FavoriteMovieDao,
) : LocalSource {
    override fun getMovies(): Flow<List<MovieDto>> {
        return movieCacheDao.getMovies().map {
            it.map { movieCacheEntity ->  movieCacheEntity.toDto() } }
    }

    override fun getFavoritesMovies(): Flow<List<MovieDto>> {
        return favoriteMovieDao.getMovies().map {
            it.map { favoriteMovieEntity -> favoriteMovieEntity.toDto() }
        }
    }

    override suspend fun getMovieById(id: Int): MovieDto {
        return movieCacheDao.getMovieById(id).toDto()
    }

    override suspend fun saveMovieToFavorites(movie: MovieDto): Long {
        return favoriteMovieDao.insertMovie(movie.toLocalFavorite())
    }

    override suspend fun saveMovies(movies: List<MovieDto>) {
        movieCacheDao.insertMovie(movies.map { it.toLocalCache() })
    }

    override suspend fun isEmpty(): Boolean {
        return movieCacheDao.itemsCount() > 0
    }

    override suspend fun deleteMovieFromFavorite(movie: MovieDto) {
        favoriteMovieDao.deleteMovie(movie.toLocalFavorite())
    }
}

