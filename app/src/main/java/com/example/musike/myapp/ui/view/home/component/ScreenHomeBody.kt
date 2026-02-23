package com.example.musike.myapp.ui.view.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.musike.myapp.data.model.remote.Playlist
import com.example.musike.myapp.data.model.usage.UiState
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.seeMoreColor
import com.example.musike.myapp.ui.theme.white

@Composable
fun ScreenHomeBody(state: UiState<List<Playlist>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

        // Group for you group
        item {
            when (state) {
                is UiState.Loading -> {
                    Text(
                        text = "Loading Data...",
                        fontSize = 20.sp,
                        color = black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                is UiState.Success -> {
                    PlaylistGroup(
                        state.mData, onClickSeeMore = {

                        }, onClickItem = { playlist ->

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }

                is UiState.Error -> {
                    Text(
                        text = "Data load failed !",
                        fontSize = 20.sp,
                        color = black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun PlaylistGroup(
    playlists: List<Playlist>,
    onClickSeeMore: () -> Unit,
    onClickItem: (Playlist) -> Unit,
    modifier: Modifier = Modifier
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(modifier = modifier) {
        PlaylistHeader(
            "Made for you", onClickSeeMore, Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp, vertical = 0.dp)
        ) {
            items(items = playlists, key = { it.id }) { playlist ->
                PlaylistItem(
                    url = playlist.imageUrl,
                    playlistName = playlist.title, artistNames = playlist.artists.joinToString(","),
                    modifier = Modifier
                        .width(screenWidth * 0.55f)
                        .aspectRatio(5f / 4f)
                        .background(white, shape = RoundedCornerShape(12.dp))
                        .clickable { onClickItem(playlist) }
                        .padding(5.dp))
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingleItem(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )

        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun PlaylistItem(
    url: String, playlistName: String, artistNames: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {

        GlideImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(21f / 9f)
                .clip(RoundedCornerShape(12.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            Text(
                text = playlistName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
            )

            Text(
                text = artistNames,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 5.dp)
            )
        }
    }
}

@Composable
fun PlaylistHeader(
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