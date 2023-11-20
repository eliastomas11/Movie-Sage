package com.example.themovieclicker.core.di.modules

import com.example.themovieclicker.core.di.modules.qualifiers.DispatcherDefault
import com.example.themovieclicker.core.di.modules.qualifiers.DispatcherIO
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.repository.MoviesRepositoryImpl
import com.example.themovieclicker.data.util.NetworkObserver
import com.example.themovieclicker.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(remoteSource: RemoteSource,localSource: LocalSource,networkObserver: NetworkObserver ,@DispatcherIO dispatcher: CoroutineDispatcher): MovieRepository{
        return MoviesRepositoryImpl(remoteSource, localSource, networkObserver, dispatcher)
    }

}