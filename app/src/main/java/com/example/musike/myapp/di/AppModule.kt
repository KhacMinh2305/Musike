package com.example.musike.myapp.di

import android.content.Context
import com.example.musike.myapp.data.config.ApiService
import com.example.musike.myapp.data.repository.playlist.PlaylistRepository
import com.example.musike.myapp.data.repository.playlist.PlaylistRepositoryImpl
import com.example.musike.myapp.data.repository.singer.SingerRepository
import com.example.musike.myapp.data.repository.singer.SingerRepositoryImpl
import com.example.musike.myapp.data.source.playlist.remote.RemotePlaylistDataSource
import com.example.musike.myapp.data.source.playlist.remote.RemotePlaylistDataSourceImpl
import com.example.musike.myapp.data.source.singer.remote.RemoteSingerDataSource
import com.example.musike.myapp.data.source.singer.remote.RemoteSingerDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://6990d23c6279728b01534f45.mockapi.io/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemotePlaylistDataSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ): RemotePlaylistDataSource {
        return RemotePlaylistDataSourceImpl(context, apiService)
    }

    @Provides
    @Singleton
    fun providePlaylistRepository(
        remoteDataSource: RemotePlaylistDataSource
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteSingerDataSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ): RemoteSingerDataSource {
        return RemoteSingerDataSourceImpl(context, apiService)
    }

    @Provides
    @Singleton
    fun provideSingerRepository(
        remoteDataSource: RemoteSingerDataSource
    ): SingerRepository {
        return SingerRepositoryImpl(remoteDataSource)
    }

}