package com.example.themovieclicker.data.repository

import android.util.Log
import com.example.themovieclicker.core.mappers.toDomain
import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.util.NetworkObserver
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val networkObserver: NetworkObserver,
    private val dispatcher: CoroutineDispatcher,
) : MovieRepository {

    private var lastPageSeen = 1

    private val TOTAL_PAGES = 40711

    override fun getMovies(page: Int): Flow<List<MovieModel>> = flow<List<MovieModel>> {
        if (localSource.isEmpty()) {
            networkObserver.checkForInternetConnection().collect { status ->
                Timber.d("REPOSITORY STATUS ${status.name}")
                when (status) {
                    NetworkObserver.Status.Available -> {
                        val remoteMovies = remoteSource.getMovies(lastPageSeen)
                        localSource.saveMovies(remoteMovies)
                        emit(remoteMovies.map { it.toDomain() })
                    }

                    else -> throw CustomException.NoNetworkConnection
                }
            }
        } else {
            emitAll(localSource.getMovies().map { response -> response.map { it.toDomain() } })
        }


    }.flowOn(dispatcher).catch { e -> throw e }

    override suspend fun saveMovieToFavorites(movie: MovieModel): Long {
        return withContext(dispatcher) {
            localSource.saveMovieToFavorites(movie.toDto())
        }
    }

    override suspend fun getMovieById(id: Int): MovieModel {
        return withContext(dispatcher) {
            localSource.getMovieById(id).toDomain()
        }
    }

    override fun getFavoritesMovies(): Flow<List<MovieModel>> {
        return localSource.getFavoritesMovies()
            .map { it.map { favoriteMovieDto -> favoriteMovieDto.toDomain() } }.flowOn(dispatcher)
    }

    override suspend fun deleteFromFavorite(movie: MovieModel) {
        withContext(dispatcher) {
            localSource.deleteMovieFromFavorite(movie.toDto())
        }
    }

    override suspend fun refresh() {
        withContext(dispatcher) {
            localSource.refresh()
            if (lastPageSeen < TOTAL_PAGES) {
                val movieResponse = remoteSource.getMovies(++lastPageSeen)
                localSource.saveMovies(movieResponse)
            } else {
                lastPageSeen = 1
                val movieResponse = remoteSource.getMovies(lastPageSeen)
                localSource.saveMovies(movieResponse)
            }
        }
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean {
        return withContext(dispatcher) {
            localSource.isMovieFavorite(movieId) > 0
        }
    }
}


