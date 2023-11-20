package com.example.themovieclicker.presentation.theme.home.components


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp
import com.example.themovieclicker.presentation.theme.ui.ClickOhAccent
import com.example.themovieclicker.presentation.theme.ui.Purple40

@Composable
fun LoadingScreen() {
  androidx.compose.material.CircularProgressIndicator(color = ClickOhAccent, strokeWidth = 3.dp)
}