package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase

import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.components.MovieCard
import java.util.Locale


@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: MovieModel, onClick: (MovieModel) -> Unit) {
    MovieCard(
        modifier = modifier,
        title = movie.title.uppercase(Locale.ROOT),
        starsAmount = movie.starAmountCalculator(),
        image = movie.imageUrl,
        onClick = onClick,
        movieModel = movie
    )
}

fun MovieModel.starAmountCalculator(): Int {
    val LOW_SCORE = 1.0..2.0
    val LOW_MID_SCORE = 3.0..4.0
    val MID_SCORE = 5.0..6.0
    val MID_HIGH_SCORE = 7.0..8.0
    val HIGH_SCORE = 9.0..10.0

    return when (this.score) {
        in LOW_SCORE -> 1
        in LOW_MID_SCORE -> 2
        in MID_SCORE -> 3
        in MID_HIGH_SCORE -> 4
        in HIGH_SCORE -> 5
        else -> {
            0
        }
    }
}