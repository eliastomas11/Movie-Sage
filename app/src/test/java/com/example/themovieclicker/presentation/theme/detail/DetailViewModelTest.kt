package com.example.themovieclicker.presentation.theme.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.themovieclicker.MainDispatcherRule
import com.example.themovieclicker.data.repository.FakeMovieRepository
import com.example.themovieclicker.presentation.theme.home.HomeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class DetailViewModelTest {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp(){
        fakeMovieRepository = FakeMovieRepository()
        savedStateHandle = SavedStateHandle(mapOf("movieId" to fakeMovieRepository.fakeMovieById.id))
        viewModel = DetailViewModel(fakeMovieRepository,savedStateHandle)
    }

    @Test
    fun `onInit ui state should be loading`() = runTest {
        assertEquals(viewModel.uiState.value.isLoading, true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onInit should get the movie with the id from the saved state handle`() = runTest {
        assertEquals(viewModel.uiState.value.detailMovie, fakeMovieRepository.fakeMovieById)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `save movie to favorites should save the movie and update the state as saved`() = runTest{
        advanceUntilIdle()
        viewModel.saveMovieToFavorite()
        viewModel.uiState.test {
            assertTrue(awaitItem().isFavorite)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when saving the same movie again the saved movie should be deleted`() = runTest{
        advanceUntilIdle()
        viewModel.saveMovieToFavorite()
        viewModel.uiState.test {
            assertTrue(awaitItem().isFavorite)
            viewModel.saveMovieToFavorite()
            assertFalse(awaitItem().isFavorite)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when checking if the movie is saved it should return true when the movie is saved`() = runTest{
        advanceUntilIdle()
        viewModel.saveMovieToFavorite()
        viewModel = DetailViewModel(fakeMovieRepository,savedStateHandle)
        advanceUntilIdle()
        viewModel.uiState.test {
            assertTrue(awaitItem().isFavorite)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when checking if the movie is saved it should return false when the movie isn't saved`() = runTest{
        advanceUntilIdle()
        viewModel.uiState.test {
            assertFalse(awaitItem().isFavorite)
        }
    }
}