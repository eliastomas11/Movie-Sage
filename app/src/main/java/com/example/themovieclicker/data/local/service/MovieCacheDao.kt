package com.example.themovieclicker.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCacheDao {

    @Query("SELECT COUNT(id) FROM movie_cache")
    fun itemsCount(): Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: List<MovieCacheEntity>)

    @Query("DELETE FROM movie_cache")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie_cache")
    fun getMovies(): Flow<List<MovieCacheEntity>>

    @Query("SELECT * FROM movie_cache WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieCacheEntity
}