package com.example.musike.myapp.data.config

import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.remote.Singer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/playlist")
    suspend fun getPlaylists(): Response<List<Playlist>>

    @GET("api/v1/single")
    suspend fun getSingers(): Response<List<Singer>>

}