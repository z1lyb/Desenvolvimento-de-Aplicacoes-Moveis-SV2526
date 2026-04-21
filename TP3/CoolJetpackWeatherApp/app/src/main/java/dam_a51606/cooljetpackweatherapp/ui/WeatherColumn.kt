package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WeatherColumn(
    temperature: Float,
    windspeed: Float,
    windDirection: Int, // º
    seaLevelPressure: Float,
    time: String
    ) {
    Column(
        modifier = Modifier.padding(16.dp), // Margem externa
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre cartões
    ) {
        WeatherCard("Temperature", "$temperature ºC")
        WeatherCard("Wind speed", "$windspeed km/h")
        WeatherCard("Wind direction", getWindDirection(windDirection))
        WeatherCard("Sea level pressure", "$seaLevelPressure")
        WeatherCard("Time", time.replace("T"," "))
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherColumnPreview() {
    WeatherColumn(
        temperature = 22.5f,
        windspeed = 12.0f,
        windDirection = 45,
        seaLevelPressure = 1013.2f,
        time = "2026-04-21T02:00"
    )
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