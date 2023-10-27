package com.example.themovieclicker.data.local.service

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: FavoriteMovieEntity): Int

    @Query("DELETE FROM favorite_movie")
    fun deleteMovieById(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getMovieById(id: Int): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movie")
    fun getMovies(): Flow<List<FavoriteMovieEntity>>
}