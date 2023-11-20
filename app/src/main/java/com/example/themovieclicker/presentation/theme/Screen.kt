package com.example.themovieclicker.presentation.theme


sealed class Screen(val route:String) {
    data object HomeScreen : Screen("home")
    data object DetailScreen : Screen("detail/")
}

