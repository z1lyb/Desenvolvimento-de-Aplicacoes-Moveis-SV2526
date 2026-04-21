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

    }
}

