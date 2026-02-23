package com.example.musike.myapp.ui.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.remote.Singer
import com.example.musike.myapp.data.model.usage.UiState
import com.example.musike.myapp.data.repository.playlist.PlaylistRepository
import com.example.musike.myapp.data.repository.singer.SingerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val playlistRepo: PlaylistRepository,
    private val singerRepo: SingerRepository
): ViewModel() {

    private val _playListDataState = MutableStateFlow<UiState<List<Playlist>>>(UiState.Loading)
    val playlistDataState = _playListDataState.asStateFlow()

    private val _singerDataState = MutableStateFlow<UiState<List<Singer>>>(UiState.Loading)
    val singerDataState = _playListDataState.asStateFlow()

    init {
        getPlaylist()
        getSinger()
    }

    private fun getPlaylist() {
        viewModelScope.launch {
            playlistRepo.getPlaylist().collect { state ->
                state.mData?.let {
                    _playListDataState.value = UiState.Success(state.mData)
                    return@collect
                }
                state.error?.let {
                    _playListDataState.value = UiState.Error(state.error)
                    return@collect
                }
            }
        }
    }

    private fun getSinger() {
        viewModelScope.launch {
            singerRepo.getSingers().collect { state ->
                state.mData?.let {
                    Log.d("SingerData", "getSinger: ${state.mData.size}")
                    _singerDataState.value = UiState.Success(state.mData)
                    return@collect
                }
                state.error?.let {
                    Log.d("SingerData", "getSinger: ${state.error}")
                    _singerDataState.value = UiState.Error(state.error)
                    return@collect
                }
            }
        }
    }

}