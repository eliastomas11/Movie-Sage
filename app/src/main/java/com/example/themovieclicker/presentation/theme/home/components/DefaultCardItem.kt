package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.R
import com.example.themovieclicker.domain.MovieModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCardItem(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    onClick: (MovieModel) -> Unit
) {
    ElevatedCard(onClick = { onClick(movie) }, modifier = modifier) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = stringResource(R.string.poster_image_content_desc) )
        Text(text = movie.title, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)

    }
}
