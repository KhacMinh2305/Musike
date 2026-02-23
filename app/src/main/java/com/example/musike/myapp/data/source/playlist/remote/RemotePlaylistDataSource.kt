package com.example.musike.myapp.data.source.playlist.remote
import com.example.musike.myapp.data.model.remote.Playlist

interface RemotePlaylistDataSource {

    suspend fun getMadeForYouPlaylists(): List<Playlist>

}