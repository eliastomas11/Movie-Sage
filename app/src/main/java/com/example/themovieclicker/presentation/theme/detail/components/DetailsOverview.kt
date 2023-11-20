package com.example.themovieclicker.presentation.theme.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.themovieclicker.R
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.components.starAmountCalculator
import com.example.themovieclicker.presentation.theme.ui.ClickOhBackgruond
import com.example.themovieclicker.presentation.theme.ui.ClickOhDetailStartGradient
import com.example.themovieclicker.presentation.theme.ui.ClickOhOnBackground


@Composable
fun DetailsOverview(movie: MovieModel?, onClick: () -> Unit, favoriteState: Boolean,onBackPressed: () -> Boolean) {
    if (movie != null) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(
                data = movie.imageUrl
            )
            .crossfade(true)
            .build()

        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageRequest,
                contentDescription = stringResource(id = R.string.poster_image_content_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    ClickOhDetailStartGradient,
                                    ClickOhBackgruond
                                ),
                                startY = 300f
                            )
                        )
                )

            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .fillParentMaxSize(0.5f)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ClickOhBackgruond
                                ),
                                tileMode = TileMode.Decal,
                                startY = 0f,
                                endY = 600f
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopStart)
                            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 68.dp)
                    ) {
                        DetailInfoItem(
                            movie = movie,
                            onClick = onClick,
                            favoriteState = favoriteState
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { onBackPressed() },modifier = Modifier.align(Alignment.TopStart).padding(2.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.button_to_navigate_back_content_desc),
                    tint = ClickOhOnBackground
                )
            }
        }
    }
}