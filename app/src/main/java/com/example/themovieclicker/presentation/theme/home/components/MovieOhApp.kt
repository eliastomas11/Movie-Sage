package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieOhApp() {
    val navController = rememberNavController()
    MovieNavHost(navController = navController)
}