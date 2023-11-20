package com.example.themovieclicker.presentation.theme.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themovieclicker.R
import com.example.themovieclicker.presentation.theme.detail.components.DetailsOverview
import com.example.themovieclicker.presentation.theme.home.components.HomeErrorScreen
import com.example.themovieclicker.presentation.theme.home.components.LoadingScreen
import timber.log.Timber

@Composable
fun DetailScreen(detailViewModel: DetailViewModel = hiltViewModel(), onBackPressed: () -> Boolean) {
    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            LoadingScreen()
        }
        if (uiState.detailMovie != null) {
            DetailsOverview(
                movie = uiState.detailMovie,
                onClick = { detailViewModel.saveMovieToFavorite() },
                favoriteState = uiState.isFavorite,
                onBackPressed = onBackPressed
            )

        } else {
            HomeErrorScreen(errorMessage = stringResource(id = R.string.movie_error_screen_message))
        }
    }


}