package com.example.musike.myapp.data.source.track.remote

import android.content.Context
import com.example.musike.myapp.data.config.ApiService
import com.example.musike.myapp.data.model.remote.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteTrackDataSourceImpl (
    private val context: Context,
    private val apiService: ApiService
): RemoteTrackDataSource {

    override suspend fun getTracks(): List<Track> = withContext(Dispatchers.IO) {
        val response = apiService.getTracks()
        if(!response.isSuccessful) throw Exception(response.errorBody()?.toString())
        return@withContext response.body() ?: emptyList()
    }

}