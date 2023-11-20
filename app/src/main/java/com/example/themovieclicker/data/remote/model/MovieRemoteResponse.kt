package com.example.themovieclicker.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieRemoteResponse(
    @SerializedName("page")val page: Int,
    @SerializedName("results")val movieResults: List<MovieResult>,
    @SerializedName("total_pages")val totalPages: Int,
    @SerializedName("total_results")val totalResults: Int
)