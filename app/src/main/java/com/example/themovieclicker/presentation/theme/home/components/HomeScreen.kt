package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.HomeUiState
import com.example.themovieclicker.presentation.theme.home.MoviePopUpState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    popUpState: MoviePopUpState,
    paddingValues: PaddingValues,
    pullRefreshState: PullRefreshState,
    onDetailAccept: (id: Int) -> Unit,
    onDismiss: () -> Unit,
    onMovieClick: (movie: MovieModel) -> Unit,
    isRefreshingState: Boolean
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (uiState) {
            is HomeUiState.Success -> MovieSuccessScreen(
                movieList = uiState.movieList,
                onClick = onMovieClick,
                moviePopUpState = popUpState,
                onDetailAccept = onDetailAccept,
                onDismiss = onDismiss,
                modifier = Modifier.matchParentSize(),
                refreshState = pullRefreshState,
                isRefreshing = isRefreshingState
            )

            is HomeUiState.Error -> HomeErrorScreen(errorMessage = uiState.message)

            is HomeUiState.Loading -> LoadingScreen()

            is HomeUiState.Refreshing -> Unit
        }
    }


}



