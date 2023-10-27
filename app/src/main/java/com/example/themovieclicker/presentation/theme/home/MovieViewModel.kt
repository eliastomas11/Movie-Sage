package com.example.themovieclicker.presentation.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState


    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies(1).collect {
                _uiState.update { movie ->
                    movie.copy(moviesList = it)
                }
            }
        }
    }

    data class HomeUiState(
        val loading: Boolean = true,
        val moviesList: List<MovieModel> = emptyList(),
        val error: String? = null,
        val popUpShowing: Boolean = false,
    )
}