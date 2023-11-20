package com.example.themovieclicker.data.repository

import com.example.themovieclicker.data.model.MovieDto
import com.example.themovieclicker.domain.MovieModel

object FakeData {

    val remoteSourceMovieListPage2 = listOf(
        MovieDto(
            id = 1,
            title = "Fake Movie 1",
            overview = "This is a fake movie 1",
            imageUrl = "http://example.com/image1.jpg",
            score = 7.2,
            releaseDate = "2023-02-15",
            originalTitle = "Original Fake Movie 1 Title"
        ),
        MovieDto(
            id = 2,
            title = "Fake Movie 2",
            overview = "This is a fake movie 2",
            imageUrl = "http://example.com/image2.jpg",
            score = 6.5,
            releaseDate = "2023-03-20",
            originalTitle = "Original Fake Movie 2 Title"
        ),
        MovieDto(
            id = 99,
            title = "Fake Movie 3",
            overview = "This is a fake movie 3",
            imageUrl = "http://example.com/image3.jpg",
            score = 8.0,
            releaseDate = "2023-05-10",
            originalTitle = "Original Fake Movie 3 Title"
        ),
        MovieDto(
            id = 53,
            title = "Fake Movie 1",
            overview = "This is a fake movie 4",
            imageUrl = "http://example.com/image4.jpg",
            score = 9.1,
            releaseDate = "2023-07-28",
            originalTitle = "Original Fake Movie 4 Title"
        )
    )
    val remoteSourceMovieListPage1 =
        listOf(
            MovieDto(
                id = 1,
                title = "Fake Movie 1",
                overview = "This is a fake movie 1",
                imageUrl = "http://example.com/image1.jpg",
                score = 7.2,
                releaseDate = "2023-02-15",
                originalTitle = "Original Fake Movie 1 Title"
            ),
            MovieDto(
                id = 2,
                title = "Fake Movie 2",
                overview = "This is a fake movie 2",
                imageUrl = "http://example.com/image2.jpg",
                score = 6.5,
                releaseDate = "2023-03-20",
                originalTitle = "Original Fake Movie 2 Title"
            ),
            MovieDto(
                id = 3,
                title = "Fake Movie 3",
                overview = "This is a fake movie 3",
                imageUrl = "http://example.com/image3.jpg",
                score = 8.0,
                releaseDate = "2023-05-10",
                originalTitle = "Original Fake Movie 3 Title"
            ),
            MovieDto(
                id = 4,
                title = "Fake Movie 4",
                overview = "This is a fake movie 4",
                imageUrl = "http://example.com/image4.jpg",
                score = 9.1,
                releaseDate = "2023-07-28",
                originalTitle = "Original Fake Movie 4 Title"
            ),
            MovieDto(
                id = 5,
                title = "Fake Movie 5",
                overview = "This is a fake movie 5",
                imageUrl = "http://example.com/image5.jpg",
                score = 5.8,
                releaseDate = "2023-09-05",
                originalTitle = "Original Fake Movie 5 Title"
            )
        )


    val localSavedMovieList =  remoteSourceMovieListPage1
}