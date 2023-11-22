package com.example.themovieclicker.presentation.theme.home.components


import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp
import com.example.themovieclicker.presentation.theme.ui.Accent

@Composable
fun LoadingScreen() {
  androidx.compose.material.CircularProgressIndicator(color = Accent, strokeWidth = 3.dp)
}