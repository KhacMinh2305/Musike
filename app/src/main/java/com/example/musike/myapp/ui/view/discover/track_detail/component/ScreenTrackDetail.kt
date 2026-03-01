package com.example.musike.myapp.ui.view.discover.track_detail.component

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.musike.R
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.white
import androidx.core.graphics.scale
import com.example.musike.myapp.ui.theme.Pink40
import com.example.musike.myapp.ui.theme.seeMoreColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ScreenTrackDetail(
    navigateBack: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    )
    {

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

        ScreenTrackDetailIconButton(R.drawable.ic_back, onClickBack)

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

        ScreenTrackDetailIconButton(R.drawable.ic_heart, onClickLike)
        ScreenTrackDetailIconButton(R.drawable.ic_more, onClickMore)
    }
}

@Composable
fun ScreenTrackBody(
    modifier: Modifier = Modifier
) {

//    AsyncImage(
//        model = =
//    )

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
            Modifier.align(Alignment.BottomCenter)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlayerBar(
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(info) {
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 12.dp, vertical = 15.dp)
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

            ScreenTrackDetailIconButton(R.drawable.ic_share) {}

            ScreenTrackDetailIconButton(R.drawable.ic_view_playlist) {}
        }
    }
}

@Preview
@Composable
fun ABC() {
    val prog by rememberUpdatedState(0.23f)

    SliderDraw(progress = prog, onProgressChange = {

    })
}

@Composable
fun SliderDraw(
    modifier: Modifier = Modifier,
    progress: Float,
    onProgressChange: (Float) -> Unit = {},
    loadThumb: (suspend (Int, Int) -> Bitmap)? = null
) {
    val tempHeight = 10
    val normalThumbSize = 30
    val highlightSize = 40
    val touchSize = 100

    var thumbImage by remember { mutableStateOf<Bitmap?>(null) }

    var thumbSize by remember { mutableIntStateOf(normalThumbSize) }

    var canDrag by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        thumbImage = withContext(Dispatchers.IO) {
            loadThumb?.invoke(thumbSize, thumbSize)?.let {
                if (it.width != thumbSize || it.height != thumbSize) {
                    it.scale(thumbSize, thumbSize)
                } else it
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            thumbImage?.recycle()
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = seeMoreColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { start: Offset ->
                        // check if start point is inside allowed area
                        val (width, height) = size
                        val halfTouchSize = touchSize.toFloat() / 2
                        val halfThumbSize = thumbSize.toFloat() / 2
                        val centerThumbX = halfThumbSize + ((width - thumbSize) * progress)
                        if (centerThumbX - halfTouchSize <= start.x && start.x <= centerThumbX + halfTouchSize) {
                            canDrag = true
                            thumbSize = highlightSize
                        }
                    },
                    onHorizontalDrag = { change: PointerInputChange, dragAmount: Float ->
                        val total = size.width - normalThumbSize
                        val updated = progress * total + dragAmount
                        onProgressChange(updated / total)
                    },
                    onDragEnd = {
                        canDrag = false
                        thumbSize = normalThumbSize
                    }
                )
            }
    ) {

        val width = size.width
        val height = size.height
        val startOffset = thumbSize.toFloat() / 2
        val barWidth = width - thumbSize

        // draw active part
        val activeWidth = barWidth * progress
        drawRoundRect(
            color = Pink40,
            topLeft = Offset(startOffset, (height - tempHeight) / 2),
            size = Size(activeWidth, tempHeight.toFloat()),
            cornerRadius = CornerRadius(0.5f),
            style = Fill
        )

        // draw inactive part
        val inactiveWidth = barWidth - activeWidth
        drawRoundRect(
            color = Pink40.copy(alpha = 0.3f),
            topLeft = Offset(startOffset + activeWidth, (height - tempHeight) / 2),
            size = Size(inactiveWidth, tempHeight.toFloat()),
            cornerRadius = CornerRadius(0.5f),
            style = Fill
        )

        // draw thumb
        val thumbX = startOffset + activeWidth - (thumbSize / 2)
        val thumbY = (height - thumbSize) / 2
        thumbImage?.let { img ->
            drawImage(
                image = img.asImageBitmap(),
                topLeft = Offset(thumbX, thumbY)
            )
        } ?: run {
            drawCircle(
                color = black,
                radius = thumbSize.toFloat() / 2,
                center = Offset(startOffset + activeWidth, height / 2)
            )
        }
    }
}

@Composable
fun ScreenTrackDetailIconButton(iconRes: Int, onClick: () -> Unit) {
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