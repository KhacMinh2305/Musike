package com.example.musike.myapp.ui.view.discover.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.musike.myapp.ui.theme.homeBodyColor
import com.example.musike.myapp.ui.theme.white
import com.example.musike.myapp.ui.viewmodel.home.HomeViewModel

@Composable
fun ScreenDiscover(
    navigatePlaylistItem: (String) -> Unit,
    navigateSingerItem: (String) -> Unit,
    navigateTrackItem: (String) -> Unit,
) {
    val viewmodel: HomeViewModel = hiltViewModel()
    val playlistState = viewmodel.playlistDataState.collectAsState()
    val singerState = viewmodel.singerDataState.collectAsState()
    val trackState = viewmodel.trackDataState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        ScreenHomeTop(
            12, 4,
            onClickNotification = {}, onClickAccount = {}
        )
        ScreenHomeBody(
            playlistState = playlistState.value,
            singerState = singerState.value,
            trackState = trackState.value,
            onReloadPlaylists = viewmodel::reloadPlaylists,
            onReloadSingers = viewmodel::reloadSingers,
            onReloadSongs = viewmodel::reloadTracks,
            navigatePlaylistItem,
            navigateSingerItem,
            navigateTrackItem,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(homeBodyColor)
        )
    }


}