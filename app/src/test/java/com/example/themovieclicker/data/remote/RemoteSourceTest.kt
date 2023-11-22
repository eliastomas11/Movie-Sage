package com.example.themovieclicker.data.remote

import com.example.themovieclicker.MainDispatcherRule
import com.example.themovieclicker.core.mappers.toDto
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.data.remote.model.MovieRemoteResponse
import com.example.themovieclicker.data.remote.model.MovieResult
import com.example.themovieclicker.data.remote.service.MovieApiService
import com.example.themovieclicker.data.repository.createFakeMovies
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class RemoteSourceTest {

    private val apiService = mockk<MovieApiService>(relaxed = true)
    private val remoteSource = RemoteSourceImpl(apiService)
    private val responseMock = mockk<Response<MovieRemoteResponse>>(relaxed = true)


    @Test
    fun `given a successful response should return the response`() = runTest {
        val remoteResponse = MovieRemoteResponse(
            page = 1,
            movieResults = listOf(
                MovieResult(
                    adult = false,
                    backdrop_path = "/backdrop.jpg",
                    genre_ids = listOf(1, 2, 3),
                    id = 123456,
                    original_language = "en",
                    original_title = "Sample Movie",
                    overview = "This is a sample movie overview.",
                    poster_path = "/poster.jpg",
                    release_date = "2023-11-20",
                    title = "Sample Movie Title",
                    vote_average = 7.5
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
        coEvery { apiService.getMoviesByPopularity(any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns remoteResponse
        assertEquals(remoteSource.getMovies(1), remoteResponse.movieResults.map { it.toDto() })
        coVerify { apiService.getMoviesByPopularity(any()) }
        verify { responseMock.isSuccessful }
        verify { responseMock.body() }

    }

    @Test(expected = CustomException.ApiException::class)
    fun `given a unsuccessful response should throw an exception`() = runTest {
        coEvery { apiService.getMoviesByPopularity(any()) } returns responseMock
        every { responseMock.isSuccessful } returns false
        remoteSource.getMovies(1)
        coVerify { apiService.getMoviesByPopularity(any()) }
        verify { responseMock.isSuccessful }

    }

    @Test
    fun `given a successful response with a null body return an empty list`() = runTest {
        coEvery { apiService.getMoviesByPopularity(any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns null
        assertEquals(remoteSource.getMovies(1), emptyList<MovieDto>())
        coVerify { apiService.getMoviesByPopularity(any()) }
        verify { responseMock.isSuccessful }
        verify { responseMock.body() }

    }
}