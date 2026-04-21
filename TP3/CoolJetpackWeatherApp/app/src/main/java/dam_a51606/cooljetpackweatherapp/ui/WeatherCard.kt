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
    windDirection: Int, // º
    seaLevelPressure: Float,
    time: String
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Função auxiliar para criar cada linha de dados
            InfoRow("Sea Level Pressure", "$seaLevelPressure hPa")
            InfoRow("Wind Direction", getWindDirection(windDirection))
            InfoRow("Wind Speed", "$windspeed km/h")
            InfoRow("Temperature", "$temperature ºC")
            InfoRow("Time", time.replace("T", " "))
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
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