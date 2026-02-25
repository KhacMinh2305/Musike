package com.example.musike.myapp.data.repository.track

import android.util.Log
import com.example.musike.myapp.data.model.remote.Track
import com.example.musike.myapp.data.model.usage.DataState
import com.example.musike.myapp.data.source.track.remote.RemoteTrackDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackRepositoryImpl(
    private val remoteDataSource: RemoteTrackDataSource
): TrackRepository {

    private val _trackState = MutableStateFlow(DataState<List<Track>>())
    val trackState = _trackState.asStateFlow()

    override suspend fun getTracks(): StateFlow<DataState<List<Track>>> {
        if (!_trackState.value.loaded) loadTracks()
        return trackState
    }

    override suspend fun reloadTracks() = loadTracks()

    private suspend fun loadTracks() {
        _trackState.value.loaded = false
        _trackState.value = try {
            DataState(remoteDataSource.getTracks(), null, true)
        } catch (e: Exception) {
            DataState(null, e.message, true)
        }
    }

}