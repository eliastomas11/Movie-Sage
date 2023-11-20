package com.example.themovieclicker.data.local.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovieclicker.data.local.service.FavoriteMovieDao
import com.example.themovieclicker.data.local.service.MovieCacheDao

@Database(entities = [MovieCacheEntity::class, FavoriteMovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieCacheDao(): MovieCacheDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}