package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCard(
    temperature: Float,
    windspeed: Float,
    windDirection: Int,
    seaLevelPressure: Float,
    time: String
) {
    // Card that shows all the weather information for the location, organised into rows
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            WeatherRow("Temperature", "$temperature ºC")
            WeatherRow("Sea level pressure", "$seaLevelPressure hPa")
            WeatherRow("Wind direction", getWindDirection(windDirection))
            WeatherRow("Wind speed", "$windspeed km/h")
            WeatherRow("Time", time.replace("T", " "))
        }
    }
}

private fun getWindDirection(dir: Int): String{
    // the wind direction is returned in degrees, so we need to "translate" it into cardinal directions
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