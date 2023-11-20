package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.themovieclicker.core.Utils.DETAIL_ID
import com.example.themovieclicker.presentation.theme.Screen
import com.example.themovieclicker.presentation.theme.detail.DetailScreen
import com.example.themovieclicker.presentation.theme.detail.DetailViewModel
import com.example.themovieclicker.presentation.theme.home.HomeViewModel


@Composable
fun MovieNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
       ) {
        composable(route = Screen.HomeScreen.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeRoute(navController = navController, viewModel = homeViewModel)
        }
        composable(
            route = Screen.DetailScreen.route + "{${DETAIL_ID}}",
            arguments = listOf(navArgument(DETAIL_ID) { type = NavType.IntType }),
        ) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailScreen(detailViewModel = detailViewModel,onBackPressed = { navController.navigateUp() })

        }

    }
}