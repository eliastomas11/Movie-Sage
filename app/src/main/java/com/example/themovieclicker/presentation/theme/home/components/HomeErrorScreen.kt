package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.themovieclicker.R

@Composable
fun HomeErrorScreen(errorMessage: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.error_image), contentDescription = stringResource(
            R.string.error_message_accesibility
        )
        )
        Text(text = errorMessage, modifier = Modifier, fontSize = 24.sp)

    }
}