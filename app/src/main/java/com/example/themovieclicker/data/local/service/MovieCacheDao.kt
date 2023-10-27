package com.example.themovieclicker.data.local.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieCacheEntity>)

    @Query("DELETE FROM movie_cache")
    fun deleteAll()

    @Query("SELECT * FROM movie_cache")
    fun getMovies(): Flow<List<MovieCacheEntity>>

    @Query("SELECT * FROM movie_cache WHERE id = :id")
    fun getMovieById(id: Int): MovieCacheEntity
}