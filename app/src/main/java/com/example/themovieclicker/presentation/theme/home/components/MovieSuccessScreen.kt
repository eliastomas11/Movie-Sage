package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.domain.FilterCategory
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.FilterBarState
import com.example.themovieclicker.presentation.theme.home.MoviePopUpState
import com.example.themovieclicker.presentation.theme.ui.Accent
import com.example.themovieclicker.presentation.theme.ui.Background

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieSuccessScreen(
    modifier: Modifier = Modifier,
    movieList: List<MovieModel>,
    moviePopUpState: MoviePopUpState,
    refreshState: PullRefreshState,
    isRefreshing: Boolean,
    onClick: (movie: MovieModel) -> Unit,
    onDetailAccept: (movieId: Int) -> Unit,
    onDismiss: () -> Unit
) {
    if (moviePopUpState.isShowing) {
        MoviePopUp(
            movie = moviePopUpState.movie,
            onAcceptClick = onDetailAccept,
            onDismiss = onDismiss
        )
    }

    Box(modifier = modifier) {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier.matchParentSize().pullRefresh(refreshState),
            content = {
                items(movieList) { movie ->
                    MovieItem(movie = movie, onClick = onClick)
                }
            })

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = Background,
            contentColor = Accent
        )
    }


}

@Composable
fun FilterBar(modifier: Modifier, onFilterClick: (FilterCategory) -> Unit,filterState: FilterBarState) {

    TopAppBar(backgroundColor = Background, contentPadding = PaddingValues(4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterCategory.values().forEach { category ->
                FilterPill(
                    category = category.name,
                    state = filterState,
                    onFilterClick = onFilterClick
                )
            }
        }


    }


}



