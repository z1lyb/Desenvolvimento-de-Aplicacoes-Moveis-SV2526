package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun WeatherRow(
    temperature: Float,
    windspeed: Float,
    windDirection: Int, // º
    seaLevelPressure: Float,
    time: String
    ) {
    Row {
        WeatherCard("Temperature", "$temperature ºC")
        WeatherCard("Wind speed", "$windspeed km/h")
        WeatherCard("Wind direction", getWindDirection(windDirection))

    }
}



private fun getWindDirection(dir: Int): String{
    return when {
        dir >= 337 || dir < 23  -> "N"
        dir in 23..67 -> "NE"
        dir in 68..112 -> "E"
        dir in 113..157 -> "SE"
        dir in 158..202 -> "S"
        dir in 203..247 -> "SW"
        dir in 248..292 -> "W"
        dir in 293..336-> "NW"
        else -> "?"
    }
}