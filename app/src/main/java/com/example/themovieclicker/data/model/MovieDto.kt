package com.example.themovieclicker.data.model

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val score: Double,
    val releaseDate: String,
    val originalTitle: String,
)