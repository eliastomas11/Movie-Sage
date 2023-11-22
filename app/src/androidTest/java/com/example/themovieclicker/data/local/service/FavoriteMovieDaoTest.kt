package com.example.themovieclicker.data.local.service

import androidx.test.filters.SmallTest
import com.example.themovieclicker.data.local.model.FavoriteMovieEntity
import com.example.themovieclicker.data.local.model.MovieDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class FavoriteMovieDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var movieFavoriteDao: FavoriteMovieDao

    @Inject
    @Named("test_db")
    lateinit var database: MovieDatabase

    @Before
    fun setUp() {
        hiltRule.inject()
        movieFavoriteDao = database.favoriteMovieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMovieShouldSaveTheMovie() = runTest {
        val movie = FavoriteMovieEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieFavoriteDao.insertMovie(movie)
        val movies = movieFavoriteDao.getMovies().first()
        assert(movies.first() == movie)

    }

    @Test
    fun deleteMovieShouldDeleteTheGivenMovie() = runTest{
        val movie = FavoriteMovieEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieFavoriteDao.insertMovie(movie)
        movieFavoriteDao.deleteMovie(movie)
        assert(movieFavoriteDao.getMovies().first().isEmpty())
    }

    @Test
    fun getFavoriteMovieByIdShouldReturnTheMovieWithTheGivenId() = runTest{
        val movie = FavoriteMovieEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieFavoriteDao.insertMovie(movie)
        val favoriteMovie = movieFavoriteDao.getMovieById(5)
        assert(favoriteMovie == movie)
    }

    @Test
    fun isFavoriteMovieShouldReturnOneIfItIsSaved() = runTest{
        val movie = FavoriteMovieEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        movieFavoriteDao.insertMovie(movie)
        val isSaved = movieFavoriteDao.isMovieFavorite(movie.id)
        assert(isSaved == 1)
    }

    @Test
    fun isFavoriteMovieShouldReturnZeroIfNotSaved() = runTest{
        val movie = FavoriteMovieEntity(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
        val isSaved = movieFavoriteDao.isMovieFavorite(movie.id)
        assert(isSaved == 0)
    }

}