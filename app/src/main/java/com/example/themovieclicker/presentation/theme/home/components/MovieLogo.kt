package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.R
import com.example.themovieclicker.presentation.theme.ui.ClickOhBackgruond
import com.example.themovieclicker.presentation.theme.ui.ClickOhOnBackground

@Composable
fun AppImageLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.movieoh_logo),
        contentDescription = stringResource(
            id = R.string.movieoh_logo
        ),
        modifier = modifier
            .background(Color.Transparent)
            .padding(top = 8.dp)
            .size(150.dp)
        ,
        colorFilter = ColorFilter.tint(
            ClickOhOnBackground
        ),
        contentScale = ContentScale.Crop,
    )
}