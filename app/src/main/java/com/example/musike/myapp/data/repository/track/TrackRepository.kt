package com.example.musike.myapp.data.repository.track

import com.example.musike.myapp.data.model.remote.Track
import com.example.musike.myapp.data.model.usage.DataState
import kotlinx.coroutines.flow.StateFlow

interface TrackRepository {

    suspend fun getTracks(): StateFlow<DataState<List<Track>>>

    suspend fun reloadTracks()

}