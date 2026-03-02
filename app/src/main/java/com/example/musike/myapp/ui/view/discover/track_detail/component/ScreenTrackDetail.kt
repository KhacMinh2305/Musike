package com.example.musike.myapp.ui.view.discover.track_detail.component

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.musike.R
import com.example.musike.myapp.domain.view.dpToPx
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.white

@Composable
fun ScreenTrackDetail(
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    )
    {

        TopBar(
            "Doan Khac Minh", navigateBack, {

            }, {

            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        ScreenTrackBody(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
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

        ScreenTrackDetailIconButton(iconRes = R.drawable.ic_back, onClick = onClickBack)

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

        ScreenTrackDetailIconButton(iconRes = R.drawable.ic_heart, onClick = onClickLike)
        ScreenTrackDetailIconButton(iconRes = R.drawable.ic_more, onClick = onClickMore)
    }
}

@Composable
fun ScreenTrackBody(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.anh_test),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        PlayerBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
        )
    }

}

@Composable
fun PlayerBar(
    modifier: Modifier = Modifier,
    clickShare: () -> Unit = {},
    clickViewPlaylist: () -> Unit = {}
) {

    ConstraintLayout(
        modifier = modifier
    ) {

        val (background, info, control) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(background) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .blur(25.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.anh_test),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        TrackPlayInfo(
            onClickShare = clickShare,
            onCLickViewPlaylist = clickViewPlaylist,
            modifier = Modifier
                .constrainAs(info) {
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(start = 12.dp, top = 15.dp, end = 12.dp, bottom = 0.dp))

//        PlayerController(
//            modifier = Modifier
//                .constrainAs(control) {
//                    width = Dimension.fillToConstraints
//                    height = Dimension.wrapContent
//                    start.linkTo(parent.start)
//                    top.linkTo(info.bottom)
//                    end.linkTo(parent.end)
//                }.padding(start = 12.dp, end = 12.dp, bottom = 15.dp)
//        )
    }
}

@Composable
fun TrackPlayInfo(
    modifier: Modifier = Modifier,
    onClickShare: () -> Unit = {},
    onCLickViewPlaylist: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            Text(
                text = "Track name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Artist in this track",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }

        ScreenTrackDetailIconButton(iconRes = R.drawable.ic_share, onClick = onClickShare)

        ScreenTrackDetailIconButton(iconRes = R.drawable.ic_view_playlist, onClick = onCLickViewPlaylist)
    }
}

@Composable
fun PlayerController(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        CustomSeekbar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { progress ->
            Log.d("Custom slider", "progress: $progress")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ScreenTrackDetailIconButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_loop
            ) {

            }

            ScreenTrackDetailIconButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_previous
            ) {

            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {

                    }
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_play),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                )
            }

            ScreenTrackDetailIconButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_next
            ) {

            }

            ScreenTrackDetailIconButton(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_volumn
            ) {

            }
        }
    }
}

@Composable
fun CustomSeekbar(
    modifier: Modifier = Modifier,
    initProgress: Float = 0.23f,
    onProgressChange: (Float) -> Unit = { }
) {

    var progress by remember { mutableFloatStateOf(initProgress) }

    var isDragging by remember { mutableStateOf(false) }

    val trackHeightDp = 5.dp
    val normalThumbSizeDp = 13.dp
    val highlightSizeDp = 15.dp
    val touchSizeDp = 50.dp

    val normalThumbSize = dpToPx(normalThumbSizeDp)
    val touchSize = dpToPx(touchSizeDp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        SeekbarDraw(
            progress = progress,
            trackHeightDp = trackHeightDp,
            thumbSizeDp = if (isDragging) highlightSizeDp else normalThumbSizeDp,
            horizontalOffset = highlightSizeDp / 2,
            startDrag = { size, point ->
                val totalWidth = size.width
                val halfTouchSize = touchSize / 2
                val centerThumbX = normalThumbSize / 2 + ((totalWidth - normalThumbSize) * progress)
                if (centerThumbX - halfTouchSize <= point.x && point.x <= centerThumbX + halfTouchSize) {
                    isDragging = true
                }
            },
            onDrag = drag@{ _, amount, size ->
                if (!isDragging) return@drag
                val totalWidth = size.width
                val max = totalWidth - normalThumbSize.toFloat()
                val newProgress = (progress + amount / max).coerceIn(0f, 1f)
                if (newProgress != progress) {
                    progress = newProgress
                    onProgressChange(newProgress)
                }
            },
            endDrag = {
                isDragging = false
            }
        )
    }
}

@Composable
fun SeekbarDraw(
    modifier: Modifier = Modifier,
    thumbImage: Bitmap? = null,
    progress: Float,
    trackHeightDp: Dp,
    thumbSizeDp: Dp,
    horizontalOffset: Dp,
    startDrag: (IntSize, Offset) -> Unit,
    onDrag: (PointerInputChange, Float, IntSize) -> Unit,
    endDrag: () -> Unit
) {

    val updatedStartDrag by rememberUpdatedState(startDrag)

    val updatedOnDrag by rememberUpdatedState(onDrag)

    val updatedEndDrag by rememberUpdatedState(endDrag)

    val trackHeight = dpToPx(trackHeightDp)
    val thumbSize = dpToPx(thumbSizeDp)
    val horizontalOffset = dpToPx(horizontalOffset)

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { start: Offset -> updatedStartDrag(size, start) },
                    onHorizontalDrag = { change, dragAmount ->
                        updatedOnDrag(
                            change,
                            dragAmount,
                            size
                        )
                    },
                    onDragEnd = updatedEndDrag
                )
            }
    ) {

        val width = size.width
        val height = size.height
        val barWidth = width - horizontalOffset * 2

        // draw active track
        val activeWidth = barWidth * progress
        drawRoundRect(
            color = white,
            topLeft = Offset(horizontalOffset, (height - trackHeight) / 2),
            size = Size(activeWidth, trackHeight),
            cornerRadius = CornerRadius(0.5f),
            style = Fill
        )

        // draw inactive track
        val inactiveWidth = barWidth - activeWidth
        drawRoundRect(
            color = white.copy(alpha = 0.5f),
            topLeft = Offset(horizontalOffset + activeWidth, (height - trackHeight) / 2),
            size = Size(inactiveWidth, trackHeight),
            cornerRadius = CornerRadius(0.5f),
            style = Fill
        )

        // draw thumb
        val thumbX = horizontalOffset + activeWidth - (thumbSize / 2)
        val thumbY = (height - thumbSize) / 2
        thumbImage?.let { img ->
            drawImage(
                image = img.asImageBitmap(),
                topLeft = Offset(thumbX, thumbY)
            )
        } ?: run {
            drawCircle(
                color = white,
                radius = thumbSize / 2,
                center = Offset(horizontalOffset + activeWidth, height / 2)
            )
        }
    }
}

@Composable
fun ScreenTrackDetailIconButton(
    modifier: Modifier = Modifier,
    iconRes: Int,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}