package dam_a51606.cooljetpackweatherapp.ui

import android.health.connect.datatypes.units.Temperature
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode

data class WeatherUIState(
    val latitude: Float,
    val longitude: Float,
    val temperature: Float,
    val windSpeed: Float,
    val windDirection: Float, // º
    val weathercode: WMO_WeatherCode,
    val seaLevelPressure: Float,
    val time: String
)