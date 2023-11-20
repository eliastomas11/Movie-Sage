package com.example.themovieclicker.presentation.theme.home

import app.cash.turbine.test
import com.example.themovieclicker.MainDispatcherRule
import com.example.themovieclicker.data.error.CustomErrors
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.repository.FakeMovieRepository
import com.example.themovieclicker.domain.FilterCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
        viewModel = HomeViewModel(fakeMovieRepository)
    }

    @Test
    fun `onInit first value should be loading`() = runTest {
        viewModel.uiState.test {
            assertEquals(HomeUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `onInit should get movies with page 1`() = runTest {
        viewModel.uiState.test {
            skipItems(1)
            assertEquals(
                awaitItem(),
                HomeUiState.Success(fakeMovieRepository.movieList)
            )
        }
    }

    @Test
    fun `on refresh should return movies in page 2`() = runTest {
        viewModel.uiState.test {
            skipItems(1)
            viewModel.onRefresh()
            assertEquals(
                HomeUiState.Success(fakeMovieRepository.movieListPage2),
                awaitItem()
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on filter click with favorite category should update state with favorite movies`() =
        runTest {
            viewModel.uiState.test {
                viewModel.onFilterClick(FilterCategory.Favorites)
                skipItems(2)
                assertEquals(
                    HomeUiState.Success(fakeMovieRepository.movieFavoriteList),
                    awaitItem()
                )
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on filter click with popular category should update state with popular movies`() =
        runTest {
            viewModel.uiState.test {
                viewModel.onFilterClick(FilterCategory.Popular)
                skipItems(1)
                assertEquals(HomeUiState.Success(fakeMovieRepository.movieList), awaitItem())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on get movies should update with error when there was an error while getting the movies`() =
        runTest(
        ) {
            fakeMovieRepository.shouldReturnError = true
            viewModel.uiState.test {
                viewModel.getMovies()
                skipItems(1)
                assertEquals(
                    HomeUiState.Error(CustomErrors.NoInternetConnection.message.toString()),
                    awaitItem()
                )
            }

        }

    @Test
    fun `on movie clicked should update pop up state to showing equals true `() {
        viewModel.onMovieClicked(fakeMovieRepository.movieList.first())
        assertTrue(viewModel.popUpState.value.isShowing)
    }


    @Test
    fun `on movie clicked should update pop up state so the movie selected is the movie clicked `() {
        viewModel.onMovieClicked(fakeMovieRepository.movieList.first())
        assertEquals(viewModel.popUpState.value.movie, fakeMovieRepository.movieList.first())
    }


}