package com.example.themovieclicker.data.repository

import com.example.themovieclicker.data.error.CustomErrors
import com.example.themovieclicker.data.error.CustomException
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.domain.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

class FakeMovieRepository : MovieRepository {

    val movieList = createFakeMovies()
    val movieListPage2 = createFakeMoviesPage2()
    val movieFavoriteList = createFakeFavoriteMovies()

    val fakeMovieById = MovieModel(
        id = 1,
        title = "Fake Movie 1",
        overview = "This is a fake movie 1",
        imageUrl = "http://example.com/image1.jpg",
        score = 7.2,
        releaseDate = "2023-02-15",
        originalTitle = "Original Fake Movie 1 Title"
    )
    var shouldReturnError = false
    override fun getMovies(page: Int): Flow<List<MovieModel>> {
        if (!shouldReturnError) {
            return flowOf(movieList).onStart { delay(1000) }
        }else{
            throw CustomException.NoNetworkConnection
        }
    }

    override suspend fun saveMovieToFavorites(movie: MovieModel): Long {
        val saved = movieFavoriteList.add(movie)
        return 1L
    }

    override suspend fun getMovieById(id: Int): MovieModel {
        if (id == fakeMovieById.id) {
            delay(1000)
            return fakeMovieById
        } else {
            throw CustomException.NoNetworkConnection
        }
    }

    override fun getFavoritesMovies(): Flow<List<MovieModel>> {
        if(shouldReturnError){
            throw CustomException.NoNetworkConnection
        }else{
            return flowOf(movieFavoriteList).onStart { delay(1000) }
        }
    }

    override suspend fun deleteFromFavorite(movie: MovieModel) {
        movieFavoriteList.remove(movie)
    }

    override suspend fun refresh() {
        movieList.clear()
        movieList.addAll(createFakeMoviesPage2())
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean {
        return movieFavoriteList.any { it.id == movieId }
    }
}


fun createFakeMovies(): MutableList<MovieModel> {
    return mutableListOf(
        MovieModel(
            id = 1,
            title = "Fake Movie 1",
            overview = "This is a fake movie 1",
            imageUrl = "http://example.com/image1.jpg",
            score = 7.2,
            releaseDate = "2023-02-15",
            originalTitle = "Original Fake Movie 1 Title"
        ),
        MovieModel(
            id = 2,
            title = "Fake Movie 2",
            overview = "This is a fake movie 2",
            imageUrl = "http://example.com/image2.jpg",
            score = 6.5,
            releaseDate = "2023-03-20",
            originalTitle = "Original Fake Movie 2 Title"
        ),
        MovieModel(
            id = 3,
            title = "Fake Movie 3",
            overview = "This is a fake movie 3",
            imageUrl = "http://example.com/image3.jpg",
            score = 8.0,
            releaseDate = "2023-05-10",
            originalTitle = "Original Fake Movie 3 Title"
        ),
        MovieModel(
            id = 4,
            title = "Fake Movie 4",
            overview = "This is a fake movie 4",
            imageUrl = "http://example.com/image4.jpg",
            score = 9.1,
            releaseDate = "2023-07-28",
            originalTitle = "Original Fake Movie 4 Title"
        ),
        MovieModel(
            id = 5,
            title = "Fake Movie 5",
            overview = "This is a fake movie 5",
            imageUrl = "http://example.com/image5.jpg",
            score = 5.8,
            releaseDate = "2023-09-05",
            originalTitle = "Original Fake Movie 5 Title"
        )
    )
}

fun createFakeMoviesPage2() = mutableListOf(
    MovieModel(
        id = 6,
        title = "Fake Movie 1",
        overview = "This is a fake movie 1",
        imageUrl = "http://example.com/image1.jpg",
        score = 7.2,
        releaseDate = "2023-02-15",
        originalTitle = "Original Fake Movie 1 Title"
    ),
    MovieModel(
        id = 4,
        title = "Fake Movie 2",
        overview = "This is a fake movie 2",
        imageUrl = "http://example.com/image2.jpg",
        score = 6.5,
        releaseDate = "2023-03-20",
        originalTitle = "Original Fake Movie 2 Title"
    ),
    MovieModel(
        id = 2,
        title = "Fake Movie 2",
        overview = "This is a fake movie 3",
        imageUrl = "http://example.com/image3.jpg",
        score = 8.0,
        releaseDate = "2023-05-10",
        originalTitle = "Original Fake Movie 3 Title"
    ),
    MovieModel(
        id = 9,
        title = "Fake Movie 9",
        overview = "This is a fake movie 4",
        imageUrl = "http://example.com/image4.jpg",
        score = 9.1,
        releaseDate = "2023-07-28",
        originalTitle = "Original Fake Movie 4 Title"
    ),
    MovieModel(
        id = 7,
        title = "Fake Movie 7",
        overview = "This is a fake movie 5",
        imageUrl = "http://example.com/image5.jpg",
        score = 5.8,
        releaseDate = "2023-09-05",
        originalTitle = "Original Fake Movie 5 Title"
    )
)

fun refreshedMovieList(): MutableList<MovieModel> {
    return mutableListOf(
        MovieModel(
            id = 1,
            title = "Fake Movie 1",
            overview = "This is a fake movie refreshed",
            imageUrl = "http://example.com/image1.jpg",
            score = 7.2,
            releaseDate = "2023-02-15",
            originalTitle = "Original Fake Movie 1 Title"
        )
    )
}

fun createFakeFavoriteMovies() = mutableListOf(
    MovieModel(
        id = 54,
        title = "Fake Movie 55",
        overview = "This is a fake movie 1",
        imageUrl = "http://example.com/image1.jpg",
        score = 7.2,
        releaseDate = "2023-02-15",
        originalTitle = "Original Fake Movie 1 Title"
    ),
    MovieModel(
        id = 11,
        title = "Fake Movie 12",
        overview = "This is a fake movie 2",
        imageUrl = "http://example.com/image2.jpg",
        score = 6.5,
        releaseDate = "2023-03-20",
        originalTitle = "Original Fake Movie 2 Title"
    )
)