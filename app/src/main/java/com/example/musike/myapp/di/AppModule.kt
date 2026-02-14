package com.example.musike.myapp.di

import com.example.musike.myapp.data.config.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(OkHttpClient.Builder().build())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}