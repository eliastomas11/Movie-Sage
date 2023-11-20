package com.example.themovieclicker.domain

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val score: Double,
    val releaseDate: String,
    val originalTitle: String,
)

