package com.example.themovieclicker.data.repository

import com.example.themovieclicker.core.di.modules.qualifiers.DispatcherIO
import com.example.themovieclicker.core.mappers.toDomain
import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.core.mappers.toLocalCache
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.util.NetworkObserver
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val networkObserver: NetworkObserver,
    private val dispatcher: CoroutineDispatcher,
) : MovieRepository {


    override fun getMovies(page: Int): Flow<List<MovieModel>> = flow<List<MovieModel>> {
        //Check for internet
        if (localSource.isEmpty()) {
            val movieResponse = remoteSource.getMovies(page)
            localSource.saveMovies(movieResponse)
        }
        localSource.getMovies().map { movieDto -> movieDto.map { it.toDomain() } }
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
        return flow<List<MovieModel>> {
            localSource.getFavoritesMovies()
                .map { it.map { favoriteMovieDto -> favoriteMovieDto.toDomain() } }
        }.flowOn(dispatcher)
    }

    override suspend fun deleteFromFavorite(movie: MovieModel) {
        withContext(dispatcher) {
            localSource.deleteMovieFromFavorite(movie.toDto())
        }
    }


}