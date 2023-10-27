package com.example.themovieclicker.data.repository

import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.remote.service.ApiServiceManager
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
) : MovieRepository {


    override fun getMovies(page: Int): Flow<List<MovieModel>> {
        return remoteSource.getMovies().map { it.to }.catch {  }
    }


}