package com.example.themovieclicker.presentation.theme.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.themovieclicker.R

@Composable
fun DetailImage(imageUrl: String,modifier: Modifier = Modifier){
    AsyncImage(model = imageUrl, contentDescription = stringResource(id = R.string.poster_image_content_desc)
    , contentScale = ContentScale.Crop, modifier = modifier)
}