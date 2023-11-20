package com.example.themovieclicker.data.local.model

import androidx.room.Entity

@Entity(tableName = "movie_cache", primaryKeys = [("id")])
data class MovieCacheEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val score: Double,
    val releaseDate: String,
    val originalTitle: String
)