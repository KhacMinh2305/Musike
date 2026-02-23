package com.example.musike.myapp.ui.view.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musike.R

@Composable
fun ScreenHomeTop(
    paddingHorizontal: Int,
    paddingVertical: Int,
    onClickNotification: () -> Unit,
    onClickAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = paddingHorizontal.dp, vertical = paddingVertical.dp)
    ) {

        Text(
            text = "Good morning Minh !",
            fontSize = 18.sp,
            color = colorResource(R.color.black),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )

        ButtonIcon(
            iconRes = R.drawable.ic_home_notification,
            pStart = 10,
            pTop = 10,
            pEnd = 7,
            pBottom = 10,
            onClick = onClickNotification
        )

        ButtonIcon(
            iconRes = R.drawable.ic_home_account,
            pStart = 7,
            pTop = 10,
            pEnd = 10,
            pBottom = 10,
            onClick = onClickAccount
        )
    }
}

@Composable
private fun ButtonIcon(
    modifier: Modifier = Modifier,
    iconRes: Int,
    pStart: Int, pTop: Int, pEnd: Int, pBottom: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .scale(1.2f)
            .clickable { onClick() }
            .padding(start = pStart.dp, top = pTop.dp, end = pEnd.dp, bottom = pBottom.dp)
    ) {
        Icon(
            painter = painterResource(iconRes),
            null,
            modifier = Modifier.wrapContentSize(),
        )
    }
}