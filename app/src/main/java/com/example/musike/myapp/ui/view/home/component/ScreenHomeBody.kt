package com.example.musike.myapp.ui.view.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musike.R
import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.remote.Singer
import com.example.musike.myapp.data.model.usage.UiState
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.seeMoreColor
import com.example.musike.myapp.ui.theme.white

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ScreenHomeBody(
    playlistState: UiState<List<Playlist>>,
    singerState: UiState<List<Singer>>,
    onReloadPlaylists: () -> Unit,
    onReloadSingers: () -> Unit,
    onReloadSongs: () -> Unit,
    modifier: Modifier = Modifier
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyColumn(modifier = modifier) {

        // Group for you group
        item {
            PlaylistGroup(
                playlistState, onClickSeeMore = {},
                onClickItem = { playlist -> },
                onClickReload = onReloadPlaylists,
                screenWidth = screenWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

        // Group Popular Singer
        item {
            SingerGroup(
                singerState, onClickSeeMore = {},
                onClickItem = { singer -> },
                onClickReload = onReloadSingers,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 0.dp, top = 10.dp, end = 0.dp, bottom = 0.dp)
            )
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun PlaylistGroup(
    playlistState: UiState<List<Playlist>>,
    onClickReload: () -> Unit,
    onClickSeeMore: () -> Unit,
    onClickItem: (Playlist) -> Unit,
    screenWidth: Dp,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        GroupHeader(
            "Made for you", onClickSeeMore, Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )

        when (playlistState) {
            is UiState.Loading -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 12.dp, vertical = 0.dp),
                ) {
                    item { PlaylistItemShimmer(screenWidth) }
                    item { PlaylistItemShimmer(screenWidth) }
                }
            }

            is UiState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    items(items = playlistState.mData, key = { it.id }) { playlist ->
                        PlaylistItem(
                            url = playlist.imageUrl,
                            playlistName = playlist.title,
                            artistNames = playlist.artists.joinToString(","),
                            modifier = Modifier
                                .width(screenWidth * 0.55f)
                                .aspectRatio(5f / 4f)
                                .background(white, shape = RoundedCornerShape(12.dp))
                                .clickable { onClickItem(playlist) }
                                .padding(5.dp))
                    }
                }
            }

            is UiState.Error -> {
                ErrorLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onClickReload()
                        }
                        .padding(horizontal = 12.dp, vertical = 20.dp)
                )
            }
        }
    }
}

@Composable
fun SingerGroup(
    state: UiState<List<Singer>>,
    onClickSeeMore: () -> Unit,
    onClickReload: () -> Unit,
    onClickItem: (Singer) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        GroupHeader(
            "Popular singer", onClickSeeMore, Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )

        when(state) {
            is UiState.Loading -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 12.dp, vertical = 0.dp),
                ) {
                    item { SingerItemShimmer() }
                    item { SingerItemShimmer() }
                    item { SingerItemShimmer() }
                    item { SingerItemShimmer() }
                    item { SingerItemShimmer() }
                    item { SingerItemShimmer() }
                }
            }

            is UiState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    items(items = state.mData, key = { it.id }) { singer ->
                        SingerItem(singer.imageUrl, singer.name, modifier = Modifier
                            .background(white, shape = RoundedCornerShape(12.dp))
                            .clickable {
                                onClickItem(singer)
                            }
                            .padding(horizontal = 8.dp, vertical = 10.dp))
                    }
                }
            }

            is UiState.Error -> {
                ErrorLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onClickReload()
                        }
                        .padding(horizontal = 12.dp, vertical = 20.dp)
                )
            }
        }
    }
}

@Composable
fun GroupHeader(
    title: String, onClickSeeMore: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = black,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

        Text(
            text = "See all",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = seeMoreColor,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    onClickSeeMore()
                }
        )
    }
}

@Composable
fun ErrorLoading(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_retry),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(25.dp)
        )

        Text(
            text = "Something went wrong !",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = black.copy(alpha = 0.5f)
        )
    }
}