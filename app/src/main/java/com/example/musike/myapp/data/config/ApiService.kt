package com.example.musike.myapp.data.config

import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.remote.Singer
import com.example.musike.myapp.data.model.remote.Track
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("api/v1/playlist")
    suspend fun getPlaylists(): Response<List<Playlist>>

    @GET("api/v1/single")
    suspend fun getSingers(): Response<List<Singer>>

    @GET
    suspend fun getTracks(
        @Url url: String = "https://raw.githubusercontent.com/KhacMinh2305/CloudTest/main/tracks.json"
    ): Response<List<Track>>

}