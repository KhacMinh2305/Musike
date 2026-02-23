package com.example.musike.myapp.data.repository.playlist

import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.usage.DataState
import kotlinx.coroutines.flow.StateFlow

interface PlaylistRepository {

    suspend fun getPlaylist(): StateFlow<DataState<List<Playlist>>>

}