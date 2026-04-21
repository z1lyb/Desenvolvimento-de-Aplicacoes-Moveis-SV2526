package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun CoordinatesCard(
    lat: String,
    lon: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit)
{
    Card() {
        Column() {
            TextField(value = lat, onValueChange = onLatitudeChange, label = { Text("Latitude") })
            TextField(value = lon, onValueChange = onLongitudeChange, label = { Text("Longitude") })
        }
    }
}