package com.example.musike.myapp.data.source.track.remote

import com.example.musike.myapp.data.model.remote.Track

interface RemoteTrackDataSource {

    suspend fun getTracks(): List<Track>

}