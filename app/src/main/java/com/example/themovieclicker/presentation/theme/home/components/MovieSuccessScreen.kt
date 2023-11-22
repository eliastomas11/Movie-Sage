package com.example.themovieclicker.presentation.theme.home.components

import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themovieclicker.domain.FilterCategory
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.FilterBarState
import com.example.themovieclicker.presentation.theme.home.MoviePopUpState
import com.example.themovieclicker.presentation.theme.ui.ClickOhBackgruond

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieSuccessScreen(
    modifier: Modifier = Modifier,
    movieList: List<MovieModel>,
    moviePopUpState: MoviePopUpState,
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
            modifier = Modifier.matchParentSize(),
            content = {
                items(movieList) { movie ->
                    MovieItem(movie = movie, onClick = onClick)
                }
            })
    }


}

@Composable
fun FilterBar(modifier: Modifier, onFilterClick: (FilterCategory) -> Unit,filterState: FilterBarState) {

    TopAppBar(backgroundColor = ClickOhBackgruond, contentPadding = PaddingValues(4.dp)) {
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



