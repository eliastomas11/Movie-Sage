package com.example.themovieclicker.core.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.local.LocalSourceImpl
import com.example.themovieclicker.data.local.model.MovieDatabase
import com.example.themovieclicker.data.local.service.FavoriteMovieDao
import com.example.themovieclicker.data.local.service.MovieCacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

     val DB_NAME = "movie_database"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideMovieCacheDao(database: MovieDatabase): MovieCacheDao {
        return database.movieCacheDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideLocalSource(
        movieCacheDao: MovieCacheDao,
        favoriteMovieDao: FavoriteMovieDao,
    ): LocalSource {
        return LocalSourceImpl(movieCacheDao, favoriteMovieDao)
    }

}