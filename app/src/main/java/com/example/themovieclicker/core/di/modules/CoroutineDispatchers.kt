package com.example.themovieclicker.core.di.modules

import com.example.themovieclicker.core.di.modules.qualifiers.DispatcherDefault
import com.example.themovieclicker.core.di.modules.qualifiers.DispatcherIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchers {

    @Provides
    @DispatcherIO
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @DispatcherDefault
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}