package com.example.themovieclicker.data.local.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: FavoriteMovieEntity): Long

    @Delete
    suspend fun deleteMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    suspend fun getMovieById(id: Int): FavoriteMovieEntity

    @Query("SELECT * FROM favorite_movie")
    fun getMovies(): Flow<List<FavoriteMovieEntity>>
    @Query("SELECT COUNT(id) FROM favorite_movie WHERE id = :movieId")
    fun isMovieFavorite(movieId: Int) : Int
}