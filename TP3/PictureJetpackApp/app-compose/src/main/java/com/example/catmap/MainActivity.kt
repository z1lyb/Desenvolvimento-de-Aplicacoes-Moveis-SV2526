package com.example.catmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.catmap.ui.navigation.NavGraph
import com.example.catmap.ui.theme.CatMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatMapTheme {
                NavGraph()
            }
        }
    }
}
