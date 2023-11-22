package com.example.themovieclicker.presentation.theme.home.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.themovieclicker.R
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.ui.Background
import com.example.themovieclicker.presentation.theme.ui.BackgroundWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(modifier: Modifier = Modifier, title: String, starsAmount: Int, image: String,onClick: (MovieModel) -> Unit,movieModel: MovieModel) {
    ElevatedCard(
        modifier = modifier
            .height(300.dp)
            .background(color = Background),
        shape = RoundedCornerShape(0.dp),
        onClick = { onClick(movieModel) },
    ) {

        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(
                data = image
            )
            .crossfade(100)
            .build()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Background)) {
            AsyncImage(
                model = imageRequest,
                contentDescription = stringResource(R.string.movie_poster_content_desc),
                modifier = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.Crop,
                alignment = Center
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Background
                            ),
                            startY = 100f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(BottomStart)
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = BackgroundWhite,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp,
                    fontFamily = FontFamily(Font(resId = R.font.bebas_neue)),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if(starsAmount == 0){
                    Spacer(modifier = Modifier.padding(8.dp).size(12.dp))
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,) {
                    for (i in 1..starsAmount) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = stringResource(R.string.star_content_desc),
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 16.dp)
                                .size(12.dp)
                        )
                    }
                    //Spacer(modifier = Modifier.width(12.dp))

                }
                Spacer(modifier = Modifier.height(24.dp))


            }

        }
    }
}

fun calculateWeight(starsAmount: Int): Float {
    return 1 / (starsAmount + 5).toFloat()
}


