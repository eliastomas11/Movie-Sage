package com.example.themovieclicker

import androidx.lifecycle.ViewModel
import com.example.themovieclicker.domain.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MovieViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState


    data class HomeUiState(
        val loading: Boolean = true,
        val movies: List<MovieModel> = emptyList(),
        val error: String? = null
    )
}