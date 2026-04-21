package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherCard(label: String, value: String) {
    Card {
        Column {
            Text(text = label)
            Text(text = value)
        }
    }
}