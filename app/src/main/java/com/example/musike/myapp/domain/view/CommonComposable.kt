package com.example.musike.myapp.domain.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.shimmerEffect(color: Color = Color.LightGray, cornerSize: Dp): Modifier = composed {

    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            color.copy(alpha = 0.6f),
            color.copy(alpha = 0.2f),
            color.copy(alpha = 0.6f),
        ),
        start = Offset(translateAnim, translateAnim),
        end = Offset(translateAnim + 200f, translateAnim + 200f)
    )

    background(brush, shape = RoundedCornerShape(cornerSize))
}