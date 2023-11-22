package com.example.themovieclicker.presentation.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.themovieclicker.R
import com.example.themovieclicker.presentation.theme.home.components.MovieOhApp
import com.example.themovieclicker.presentation.theme.ui.Background
import com.example.themovieclicker.presentation.theme.ui.GradientDetail
import com.example.themovieclicker.presentation.theme.ui.TheMovieClickerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheMovieClickerTheme {
                Surface {
                    MovieOhApp()
                }
                //DetailsTest(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Preview
    @Composable
    fun DetailsTest(modifier: Modifier = Modifier) {
        val swipeableState = rememberScrollState()
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://th.bing.com/th/id/OIP.vnGOaFJhenpNROeLpVEi1gHaJ4?pid=ImgDet&rs=1",
                contentDescription = stringResource(id = R.string.poster_image_content_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                GradientDetail,
                                Background
                            ),
                            startY = 300f
                        )
                    )
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(
                    modifier = Modifier
                        .fillParentMaxSize(0.9f)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    GradientDetail,
                                    Background
                                ),
                                startY = 0f
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopStart)
                    ) {
                        Text(text = "Title: Texto 1 ")
                        Text(text = "Title: Texto 1 ")
                        Text(text = "Title: Texto 1 ")
                        Text(text = "Title: Texto 1 ")
                    }
                }
            }

        }
    }

}





