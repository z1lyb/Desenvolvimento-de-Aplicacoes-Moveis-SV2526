package dam_a51606.cooljetpackweatherapp.ui

import android.health.connect.datatypes.units.Temperature
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode

data class WeatherUIState(
    val latitude: Float = 0.0f,
    val longitude: Float = 0.0f,
    val temperature: Float = 0.0f,
    val windspeed: Float = 0.0f,
    val winddirection: Int = 0, // º
    val weathercode: Int = 0,
    val seaLevelPressure: Float = 0.0f,
    val time: String = ""
)