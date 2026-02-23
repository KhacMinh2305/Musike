package com.example.musike.myapp.data.source.playlist.remote

import android.content.Context
import com.example.musike.myapp.data.config.ApiService
import com.example.musike.myapp.data.model.remote.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemotePlaylistDataSourceImpl(
    private val context: Context,
    private val apiService: ApiService
): RemotePlaylistDataSource {

    override suspend fun getMadeForYouPlaylists(): List<Playlist> = withContext(Dispatchers.IO) {
        val response = apiService.getPlaylists()
        if(!response.isSuccessful) throw Exception(response.errorBody()?.toString())
        return@withContext response.body() ?: emptyList()
    }

}