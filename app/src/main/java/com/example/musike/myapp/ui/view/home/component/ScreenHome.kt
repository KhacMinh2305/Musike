package com.example.musike.myapp.ui.view.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.musike.myapp.ui.viewmodel.home.HomeViewModel

@Composable
fun ScreenHome() {
    val viewmodel: HomeViewModel = hiltViewModel()
    Column(modifier = Modifier.Companion.fillMaxSize()) {
        ScreenHomeTop(
            12, 12,
            onClickNotification = {}, onClickAccount = {}
        )
    }
}