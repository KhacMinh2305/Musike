package com.example.musike.myapp.ui.view.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.musike.R
import com.example.musike.myapp.domain.view.shimmerEffect
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.white

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
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.img_placeholder),
            error = painterResource(R.drawable.img_image_error),
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingerItem(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .clip(CircleShape)
        )

        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            minLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp)
        )
    }
}

@Composable
fun PlaylistItemShimmer(
    screenWidth: Dp
) {
    val width = screenWidth * 0.55f
    val height = width * 4f / 5f
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .shimmerEffect(cornerSize = 12.dp)
    )
}

@Composable
fun SingerItemShimmer() {
    Box(
        modifier = Modifier
            .width(69.dp)
            .height(100.dp)
            .shimmerEffect(cornerSize = 12.dp)
    )
}