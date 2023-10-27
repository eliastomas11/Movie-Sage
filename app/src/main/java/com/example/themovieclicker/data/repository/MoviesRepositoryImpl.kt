package com.example.themovieclicker.data.repository

import com.example.themovieclicker.core.mappers.toDomain
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
) : MovieRepository {


    override fun getMovies(page: Int): Flow<List<MovieModel>> = flow<List<MovieModel>> {
        //Check Network Excepction
        remoteSource.getMovies().map { it.map { movieDto -> movieDto.toDomain() } }
    }

    override fun saveMovieToFavorites(movie: MovieModel): Int {
        return localSource.saveMovieToFavorites(movie.toDto())
    }


}