package com.example.musike.myapp.ui.view.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.musike.myapp.ui.theme.MusikeTheme
import com.example.musike.myapp.ui.view.home.component.ScreenHomeTop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusikeTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    ScreenHomeTop(
                        12, 30,
                        onClickNotification = {
                            Toast.makeText(this@HomeActivity, "notification", Toast.LENGTH_SHORT)
                                .show()
                        }, onClickAccount = {
                            Toast.makeText(this@HomeActivity, "account", Toast.LENGTH_SHORT)
                                .show()
                        })
                }
            }
        }
    }

}