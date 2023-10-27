package com.example.themovieclicker.presentation.theme

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.MovieItem
import com.example.themovieclicker.presentation.theme.home.MovieViewModel
import com.example.themovieclicker.presentation.theme.home.components.DefaultCardItem
import com.example.themovieclicker.presentation.theme.ui.TheMovieClickerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieClickerTheme {
                val movieUiState by remember { viewModel.uiState }.collectAsState()
                HomeScreen(movieList = movieUiState.moviesList)
            }

        }

    }
}


@Composable
fun HomeScreen(movieList: List<MovieModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(movieList) { movie ->
                val context = LocalContext.current
                MovieItem(movie = movie)
                DefaultCardItem(movie = movie, onClick = { showToast(context)} )
            }
        })
}


fun showToast(context: Context){
    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
}