package com.example.themovieclicker.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun checkForInternetConnection(): Flow<Status>

    enum class Status{
        Available,
        Unavailable,
        Lost;
    }
}