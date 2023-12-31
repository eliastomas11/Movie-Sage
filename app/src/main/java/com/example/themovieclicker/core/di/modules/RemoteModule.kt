package com.example.themovieclicker.core.di.modules

import android.content.Context
import com.example.themovieclicker.core.di.modules.qualifiers.MovieRemoteInterceptor
import com.example.themovieclicker.data.remote.RemoteSource
import com.example.themovieclicker.data.remote.RemoteSourceImpl
import com.example.themovieclicker.data.remote.service.MovieApiService
import com.example.themovieclicker.data.remote.service.MovieServiceInterceptor
import com.example.themovieclicker.data.util.NetworkObserver
import com.example.themovieclicker.data.util.NetworkObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @MovieRemoteInterceptor
    @Provides
    fun provideMoviesInterceptor(): Interceptor {
        return MovieServiceInterceptor()
    }

    @Provides
    fun provideNetworkClient(@MovieRemoteInterceptor movieInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(movieInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideApiService(networkService: Retrofit): MovieApiService {
        return networkService.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkObserver(@ApplicationContext context: Context): NetworkObserver {
        return NetworkObserverImpl(context)
    }

    @Provides
    @Singleton
    fun provideRemoteSource(apiService: MovieApiService): RemoteSource {
        return RemoteSourceImpl(apiService)
    }
}