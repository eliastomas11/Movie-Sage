package com.example.themovieclicker.core.mappers

import android.graphics.Movie
import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.model.MovieRemoteResponse
import com.example.themovieclicker.data.remote.model.MovieResult
import com.example.themovieclicker.domain.MovieModel

private const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

fun MovieResult.toDto(): MovieDto =
    MovieDto(
        id = this.id,
        title = this.title,
        imageUrl = "$IMAGE_URL${this.poster_path}",
        overview = this.overview,
        score = this.vote_average,
    )


fun MovieDto.toDomain(): MovieModel =
    MovieModel(
        id = this.id,
        title = this.title,
        overview = this.overview,
        imageUrl = this.imageUrl,
        score = this.score
    )

fun MovieDto.toLocalCache(): MovieCacheEntity =
    MovieCacheEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        imageUrl = this.imageUrl,
        score = this.score
    )

fun MovieDto.toLocalFavorite(): FavoriteMovieEntity =
    FavoriteMovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        imageUrl = this.imageUrl,
        score = this.score
    )

fun MovieCacheEntity.toDto(): MovieDto = MovieDto(
    id = this.id,
    title = this.title,
    overview = this.overview,
    imageUrl = this.imageUrl,
    score = this.score
)

fun MovieModel.toDto(): MovieDto = MovieDto(
    id = this.id,
    title = this.title,
    overview = this.overview,
    imageUrl = this.imageUrl,
    score = this.score
)

fun FavoriteMovieEntity.toDto(): MovieDto = MovieDto(
    id = this.id,
    title = this.title,
    overview = this.overview,
    imageUrl = this.imageUrl,
    score = this.score
)
