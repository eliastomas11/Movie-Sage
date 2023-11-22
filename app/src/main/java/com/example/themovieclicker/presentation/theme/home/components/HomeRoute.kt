package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.themovieclicker.presentation.theme.Screen
import com.example.themovieclicker.presentation.theme.home.HomeViewModel
import com.example.themovieclicker.presentation.theme.home.MoviePopUpState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val popUpState by viewModel.popUpState
    val refreshState by viewModel.refreshingState
    val filterBarState by viewModel.filterState

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshState,
        onRefresh = viewModel::onRefresh
    )

    Scaffold(modifier = Modifier,
        topBar = {
            FilterBar(
                modifier = Modifier.fillMaxWidth(),
                onFilterClick = { filterCategory -> viewModel.onFilterClick(filterCategory) },
                filterState = filterBarState)
        },
        content = {
            HomeScreen(
                uiState = uiState,
                paddingValues = it,
                pullRefreshState = pullRefreshState,
                onDetailAccept = { id ->
                    navController.navigate(Screen.DetailScreen.route + "$id")
                    viewModel.onNavigateToDetailScreen(id)
                },
                onMovieClick = viewModel::onMovieClicked,
                popUpState = popUpState,
                onDismiss = viewModel::onDismiss
            )
        }
    )
}
