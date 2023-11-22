package com.example.themovieclicker.data.local.service

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.local.model.MovieDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.internal.toImmutableList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@HiltAndroidTest
class MovieCacheDaoTest {

    private lateinit var movieCacheDao: MovieCacheDao

    @Inject
    @Named("test_db")
    private lateinit var database: MovieDatabase

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        movieCacheDao = database.movieCacheDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMovieShouldSaveTheMovie() = runTest {
        val movie = MovieCacheEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieCacheDao.insertMovie(listOf(movie))
        val movies = movieCacheDao.getMovies().first()
        assert(movies.first() == movie)

    }

    @Test
    fun deleteMoviesShouldDeleteAllMovies() = runTest{
        val movie = MovieCacheEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieCacheDao.insertMovie(listOf( movie,movie,movie))
        movieCacheDao.deleteAll()
        assert(movieCacheDao.getMovies().first().isEmpty())
    }

    @Test
    fun getMovieByIdShouldReturnTheMovieWithTheGivenId() = runTest{
        val movie = MovieCacheEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieCacheDao.insertMovie(listOf( movie))
        movieCacheDao.getMovieById(5)
        val movieList = movieCacheDao.getMovies().first()
        assert(movieList.first() == movie)
    }



}