package com.example.themovieclicker.presentation.theme.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieclicker.data.error.CustomErrors
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState())
    val uiState: StateFlow<DetailUiState> get() = _uiState.asStateFlow()

    init {
        val movieId: Int? = savedStateHandle["movieId"]
        onInit(movieId)
    }

    private fun onInit(movieId: Int?) {
        if (movieId != null) {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                try {
                    val movie = repository.getMovieById(id = movieId)
                    checkIfSaved(movieId = movieId)
                    _uiState.update { it.copy(detailMovie = movie) }
                } catch (e: CustomException) {
                    _uiState.update { it.copy(error = e.mapToCustomError()) }
                } catch (e: Exception) {
                    Timber.e(e.message)
                    _uiState.update { it.copy(error = CustomErrors.UnknownError) }

                } finally {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        } else {
            _uiState.update { it.copy(error = CustomErrors.UnknownError) }
        }
    }

    fun saveMovieToFavorite() {
        viewModelScope.launch {
            try {
                if (_uiState.value.isFavorite) {
                    deleteMovieFromFavorite(_uiState.value.detailMovie!!)
                    _uiState.update { it.copy(isFavorite = false) }
                } else {
                    val isSaved = repository.saveMovieToFavorites(_uiState.value.detailMovie!!) > 0L
                    if (isSaved) {
                        _uiState.update { it.copy(isFavorite = true) }
                    } else {
                        _uiState.update { it.copy(error = CustomErrors.FailToSaveError) }
                    }
                }
            } catch (e: CustomException) {
                _uiState.update { it.copy(error = e.mapToCustomError()) }
                _uiState.update { it.copy(isFavorite = false) }
            } catch (e: Exception) {
                Timber.e(e.message)
                _uiState.update { it.copy(error = CustomErrors.UnknownError) }
            }
        }
    }

    private suspend fun checkIfSaved(movieId:Int){
        _uiState.update {
            it.copy(isFavorite = repository.isMovieFavorite(movieId))
        }
    }

    private fun deleteMovieFromFavorite(movie: MovieModel) {
        viewModelScope.launch {
            try {
                repository.deleteFromFavorite(movie)
            } catch (e: CustomException) {
                _uiState.update { it.copy(error = e.mapToCustomError()) }
            } catch (e: Exception) {
                Timber.e(e.message)
                _uiState.update { it.copy(error = CustomErrors.UnknownError) }
            }
        }
    }

}

data class DetailUiState(
    val isLoading: Boolean = true,
    val detailMovie: MovieModel? = null,
    val error: CustomErrors? = null,
    val isFavorite: Boolean = false,
)
