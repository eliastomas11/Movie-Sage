package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.themovieclicker.R
import com.example.themovieclicker.core.Utils.format
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.ui.ClickOhAccent
import com.example.themovieclicker.presentation.theme.ui.ClickOhBackgruond
import com.example.themovieclicker.presentation.theme.ui.ClickOhOnBackground
import timber.log.Timber


@Composable

fun MoviePopUp(
    modifier: Modifier = Modifier,
    movie: MovieModel? = null,
    onDismiss: () -> Unit,
    onAcceptClick: (movieId: Int) -> Unit,
) {

    if (movie != null) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true,
            )
        ) {
            PopUpCard(
                title = movie.title,
                score = movie.score,
                id = movie.id,
                imageUrl = movie.imageUrl,
                onAcceptClick = onAcceptClick,
                onDismiss = onDismiss
            )
        }

    }
}

@Composable
fun PopUpCard(
    modifier: Modifier = Modifier,
    title: String,
    score: Double,
    id: Int,
    imageUrl: String,
    onAcceptClick: (movieId: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ClickOhBackgruond),
        modifier = modifier.height(400.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(id = R.string.poster_image_content_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                ClickOhBackgruond
                            ),
                            startY = 100f
                        )
                    )
            )
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.cancel_pop_up_button_content_desc),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = ClickOhOnBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        fontFamily = FontFamily(Font(resId = R.font.bebas_neue)),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = stringResource(R.string.star_content_desc),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = score.format(),
                        color = ClickOhOnBackground,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily(Font(resId = R.font.bebas_neue)),
                    )


                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    ClickOhBackgruond,
                                    ClickOhAccent
                                ),
                            )
                        )
                        .blur(108.dp)
                        .clip(shape = MaterialTheme.shapes.small.copy(CornerSize(8.dp)))
                        .clickable { onAcceptClick(id) }
                        .padding(16.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.detail_popup_accept),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Center)
                    )
                }
            }


        }

    }

}




