package com.example.themovieclicker.data.local.service

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.themovieclicker.data.local.model.MovieCacheEntity
import com.example.themovieclicker.data.local.model.MovieDatabase
import com.example.themovieclicker.data.model.MovieDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieCacheDaoTest {

    private lateinit var movieCacheDao: MovieCacheDao
    private lateinit var database: MovieDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
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
        Assert.assertEquals(movies.first(), movie)

    }
}