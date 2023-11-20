package com.example.themovieclicker.presentation.theme.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieclicker.data.error.CustomErrors
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.domain.FilterCategory
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> get() = _uiState

    var refreshingState = mutableStateOf(false)
        private set

    var popUpState = mutableStateOf(MoviePopUpState())
        private set

    init {
        getMovies()
    }

    fun getMovies() {
        _uiState.value = HomeUiState.Loading
        viewModelScope.launch {
            try {
                repository.getMovies(1).collect { movies ->
                    _uiState.update {
                        HomeUiState.Success(movies)
                    }
                }
            } catch (e: CustomException) {
                _uiState.update {
                    Timber.e(e.message)
                    Timber.tag("CustomException").d(e.mapToCustomError().message.toString())
                    HomeUiState.Error(e.mapToCustomError().message.toString())
                }
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }

    }

    fun onMovieClicked(movie: MovieModel) {
        popUpState.value = popUpState.value.copy(isShowing = true, movie = movie)
    }

    fun onRefresh() {
        viewModelScope.launch {
            repository.refresh()
            HomeUiState.Refreshing(true)
        }
    }

    fun onNavigateToDetailScreen(movieId: Int) {
        popUpState.value = popUpState.value.copy(isShowing = false, movie = null)
    }

    fun onDismiss() {
        popUpState.value = popUpState.value.copy(isShowing = false, movie = null)
    }

    fun onFilterClick(filterCategory: FilterCategory){
        when (filterCategory){
            FilterCategory.Popular -> {
                getMovies()
            }
            FilterCategory.Favorites -> {
                getFavoriteMovies()
            }
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState.Loading
                repository.getFavoritesMovies().collect{ movies ->
                    _uiState.update {
                        HomeUiState.Success(movies)
                    }
                }
            } catch (e: CustomException) {
                _uiState.update {
                    HomeUiState.Error(e.mapToCustomError().message.toString())
                }
            }catch (e:Exception){
                _uiState.update {
                    HomeUiState.Error(CustomErrors.UnknownError.message.toString())
                }
            }
        }

    }

}

data class MoviePopUpState(val isShowing: Boolean = false, val movie: MovieModel? = null)


sealed class HomeUiState() {
    data class Refreshing(val isRefreshing: Boolean = false) : HomeUiState()

    data class Success(val movieList: List<MovieModel>) : HomeUiState()

    data object Loading : HomeUiState()

    data class Error(val message: String) : HomeUiState()

}
