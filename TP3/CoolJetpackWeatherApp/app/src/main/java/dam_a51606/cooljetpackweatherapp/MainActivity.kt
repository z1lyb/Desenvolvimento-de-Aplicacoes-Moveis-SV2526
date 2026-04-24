package dam_a51606.cooljetpackweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import dam_a51606.cooljetpackweatherapp.ui.WeatherUI
import dam_a51606.cooljetpackweatherapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                WeatherUI()
            }
        }
    }

}