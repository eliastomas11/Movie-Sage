package com.example.themovieclicker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie", primaryKeys = [("id")])
data class FavoriteMovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val score: Double,
    val releaseDate: String,
    val originalTitle: String
)