package com.example.musike.myapp.ui.view.discover.track_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.musike.R
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.white

@Composable
fun ScreenTrackDetail(
    navigateBack: () -> Unit
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(white)) {
        item {
            TopBar(
                "Doan Khac Minh", navigateBack, {

                }, {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun TopBar(
    title: String,
    onClickBack: () -> Unit,
    onClickLike: () -> Unit,
    onClickMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        ScreenTrackDetailButton(R.drawable.ic_back, onClickBack)

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )

        ScreenTrackDetailButton(R.drawable.ic_heart, onClickLike)
        ScreenTrackDetailButton(R.drawable.ic_more, onClickMore)
    }
}

@Composable
fun ScreenTrackDetailButton(iconRes: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}