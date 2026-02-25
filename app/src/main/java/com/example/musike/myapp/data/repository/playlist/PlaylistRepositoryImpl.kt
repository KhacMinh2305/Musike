package com.example.musike.myapp.data.repository.playlist
import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.usage.DataState
import com.example.musike.myapp.data.source.playlist.remote.RemotePlaylistDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaylistRepositoryImpl(
    private val remoteDataSource: RemotePlaylistDataSource
) : PlaylistRepository {

    private val _playlistState = MutableStateFlow(DataState<List<Playlist>>())
    val playlistFlow = _playlistState.asStateFlow()

    override suspend fun getPlaylist(): StateFlow<DataState<List<Playlist>>> {
        if (!_playlistState.value.loaded) loadPlaylists()
        return playlistFlow
    }

    override suspend fun reloadPlaylists() = loadPlaylists()

    private suspend fun loadPlaylists() {
        _playlistState.value.loaded = false
        _playlistState.value = try {
            DataState(remoteDataSource.getMadeForYouPlaylists(), null, true)
        } catch (e: Exception) {
            DataState(null, e.message, true)
        }
    }

}