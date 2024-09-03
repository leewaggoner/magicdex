package com.wreckingballsoftware.magicdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wreckingballsoftware.magicdex.ui.navigation.MagicDexHost
import com.wreckingballsoftware.magicdex.ui.theme.MagicDexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MagicDexTheme {
                MagicDexHost()
            }
        }
    }
}
