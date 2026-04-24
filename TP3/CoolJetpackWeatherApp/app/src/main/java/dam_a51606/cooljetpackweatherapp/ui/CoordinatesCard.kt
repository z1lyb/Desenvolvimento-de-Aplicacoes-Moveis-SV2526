package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dam_a51606.cooljetpackweatherapp.R

@Composable
fun CoordinatesCard(
    lat: String,
    lon: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit) {

    // Card with text fields where the user can select latitude and longitude
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(stringResource(R.string.coordinates_label), style = MaterialTheme.typography.titleMedium)
            TextField(
                value = lat,
                onValueChange = onLatitudeChange,
                label = { Text(stringResource(R.string.latitude_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = lon,
                onValueChange = onLongitudeChange,
                label = { Text(stringResource(R.string.longitude_label)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}