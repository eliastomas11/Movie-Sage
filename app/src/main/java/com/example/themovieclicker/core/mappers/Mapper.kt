package com.example.themovieclicker.core.mappers

import android.graphics.Movie
import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.model.MovieRemoteResponse
import com.example.themovieclicker.data.remote.model.MovieResult

fun MovieResult.toDto(): MovieDto = MovieDto(
    id = this.id,
    title = this.title,
    imageUrl = this.poster_path,
    score = this.vote_average,
)