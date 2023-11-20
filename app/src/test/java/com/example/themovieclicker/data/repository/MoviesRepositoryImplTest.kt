package com.example.themovieclicker.data.repository

import com.example.themovieclicker.MainDispatcherRule
import com.example.themovieclicker.core.mappers.toDomain
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.local.LocalSource
import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.util.NetworkObserver
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    @MockK(relaxed = true)
    lateinit var remoteSource: RemoteSource

    @MockK(relaxed = true)
    lateinit var localSource: LocalSource

    @MockK
    lateinit var networkObserver: NetworkObserver

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        moviesRepositoryImpl = MoviesRepositoryImpl(
            remoteSource,
            localSource,
            networkObserver,
            mainDispatcherRule.testDispatcher
        )

    }

    @Test
    fun `when getting movies from local source and local source is empty should return movies from remote source`() =
        runTest {
            coEvery { localSource.isEmpty() } returns true
            coEvery { remoteSource.getMovies(1) } returns FakeData.remoteSourceMovieListPage1
            coEvery { networkObserver.checkForInternetConnection() } returns flowOf(NetworkObserver.Status.Available)
            val movieList = moviesRepositoryImpl.getMovies(1).first()
            assertEquals(
                movieList,
                FakeData.remoteSourceMovieListPage1.map { it.toDomain() }
            )
            coVerify { localSource.isEmpty() }
            coVerify { remoteSource.getMovies(1) }
            coVerify { networkObserver.checkForInternetConnection() }
        }

    @Test
    fun `when getting movies from local source and local source is not empty should return movies from local source`() =
        runTest {
            coEvery { localSource.isEmpty() } returns false
            coEvery { localSource.getMovies() } returns flowOf(FakeData.localSavedMovieList)
            val movieList = moviesRepositoryImpl.getMovies(1).first()
            assertEquals(
                movieList,
                FakeData.localSavedMovieList.map { it.toDomain() }
            )
            coVerify { localSource.isEmpty() }
        }

    @Test
    fun `when getting movies from local source and local source is not empty should not call network observer`() =
        runTest {
            coEvery { localSource.isEmpty() } returns false
            coEvery { localSource.getMovies() } returns flowOf(FakeData.localSavedMovieList)
            coEvery { networkObserver.checkForInternetConnection() } returns flowOf(NetworkObserver.Status.Available)

            moviesRepositoryImpl.getMovies(1).first()

            coVerify(exactly = 0) { networkObserver.checkForInternetConnection() }
        }

    @Test
    fun `when getting movies from local source and local source is not empty should not call remote source`() =
        runTest {
            coEvery { localSource.isEmpty() } returns false
            coEvery { localSource.getMovies() } returns flowOf(FakeData.localSavedMovieList)
            coEvery { remoteSource.getMovies(1) } returns FakeData.remoteSourceMovieListPage1

            moviesRepositoryImpl.getMovies(1)

            coVerify(exactly = 0) { remoteSource.getMovies(1) }
        }

    @Test(expected = CustomException.NoNetworkConnection::class)
    fun `when getting movies from local source and local source is empty and there is no internet connection should throw exception`() =
        runTest {
            coEvery { localSource.isEmpty() } returns true
            coEvery { networkObserver.checkForInternetConnection() } returns flowOf(NetworkObserver.Status.Unavailable)

            moviesRepositoryImpl.getMovies(1).first()

            coVerify { networkObserver.checkForInternetConnection() }
            coVerify { localSource.isEmpty() }

        }

    @Test()
    fun `when getting movies from local source and local source is empty should get movies from remote, saved them and return the same movies`() =
        runTest {
            coEvery { localSource.isEmpty() } returns true
            coEvery { remoteSource.getMovies(1) } returns FakeData.remoteSourceMovieListPage1
            coEvery { networkObserver.checkForInternetConnection() } returns flowOf(NetworkObserver.Status.Available)

            moviesRepositoryImpl.getMovies(1).first()
            val movieList2 = moviesRepositoryImpl.getMovies(1).first()

            assertEquals(movieList2, FakeData.remoteSourceMovieListPage1.map { it.toDomain() })

            coVerify { localSource.isEmpty() }
            coVerify { remoteSource.getMovies(1) }
            coVerify { networkObserver.checkForInternetConnection() }
        }

    @Test()
    fun `when getting movies from remote source the movies should be saved in local source`() =
        runTest {
            coEvery { localSource.isEmpty() } returns true
            coEvery { remoteSource.getMovies(1) } returns FakeData.remoteSourceMovieListPage1
            coEvery { networkObserver.checkForInternetConnection() } returns flowOf(NetworkObserver.Status.Available)
            coEvery { localSource.saveMovies(any()) }

            moviesRepositoryImpl.getMovies(1).first()

            coVerify { localSource.isEmpty() }
            coVerify { remoteSource.getMovies(1) }
            coVerify { networkObserver.checkForInternetConnection() }
            coVerify { localSource.saveMovies(withArg { assertTrue(it == FakeData.remoteSourceMovieListPage1) }) }
        }

    @Test()
    fun `when refresh is called and last page seen is less than total pages should get movies from remote with the next page`() =
        runTest {
            coEvery { remoteSource.getMovies(1) }
            moviesRepositoryImpl.refresh()
            coVerify { remoteSource.getMovies(withArg { assertTrue(it == 2) }) }
        }

    @Test()
    fun `when isMovieFavorite is called should return true if the movie is favorite`() =
        runTest {
            coEvery { localSource.isMovieFavorite(any()) } returns 1
            val result = moviesRepositoryImpl.isMovieFavorite(1)
            assertTrue(result)
            coVerify { localSource.isMovieFavorite(withArg { assertTrue(it == 1) }) }
        }

    @Test()
    fun `when refresh is called it should refresh local source and save the new movies`() =
        runTest {
            coEvery { localSource.saveMovies(any()) } returns Unit
            coEvery { remoteSource.getMovies(any()) } returns FakeData.remoteSourceMovieListPage2
            moviesRepositoryImpl.refresh()
            coVerify { localSource.saveMovies(withArg { assertTrue(it == FakeData.remoteSourceMovieListPage2) }) }
            coVerify { remoteSource.getMovies(any()) }
            coVerify { localSource.refresh() }
        }


}
