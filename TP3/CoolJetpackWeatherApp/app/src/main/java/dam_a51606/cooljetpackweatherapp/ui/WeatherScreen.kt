package dam_a51606.cooljetpackweatherapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode
import dam_a51606.cooljetpackweatherapp.data.getWeatherCodeMap
import dam_a51606.cooljetpackweatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel = viewModel()) {

    val weatherUIState by weatherViewModel.uiState.collectAsState()
    val latitude = weatherUIState.latitude
    val longitude = weatherUIState.longitude
    val temperature = weatherUIState.temperature
    val windSpeed = weatherUIState.windspeed
    val windDirection = weatherUIState.winddirection
    val weathercode = weatherUIState.weathercode
    val seaLevelPressure = weatherUIState.seaLevelPressure
    val time = weatherUIState.time
    val is_day = weatherUIState.is_day

    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeWeatherUI(
            latitude,
            longitude,
            temperature,
            windSpeed,
            windDirection,
            weathercode,
            seaLevelPressure,
            time,
            is_day,
            onLatitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let {
                    weatherViewModel.updateLatitude(it)
                }
            },
            onLongitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let {
                    weatherViewModel.updateLongitude(it)
                }
            },
            onUpdateButtonClick = {
                weatherViewModel.fetchWeather()
            }
        )
    } else {
        PortraitWeatherUI(
            latitude,
            longitude,
            temperature,
            windSpeed,
            windDirection,
            weathercode,
            seaLevelPressure,
            time,
            is_day,
            onLatitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let {
                    weatherViewModel.updateLatitude(it)
                }
            },
            onLongitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let {
                    weatherViewModel.updateLongitude(it)
                }
            },
            onUpdateButtonClick = {
                weatherViewModel.fetchWeather()
            }
        )
    }
}

/**
 * Application UI, in portrait mode
 */
@Composable
fun PortraitWeatherUI(
    latitude: Float,
    longitude: Float,
    temperature: Float,
    windSpeed: Float,
    windDirection: Int,
    weathercode: Int,
    seaLevelPressure: Float,
    time: String,
    is_day: Int,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WeatherIcon(
            weathercode = weathercode,
            is_day = is_day
        )

        CoordinatesCard(
            latitude.toString(),
            longitude.toString(),
            onLatitudeChange,
            onLongitudeChange
        )

        WeatherCard(
            temperature,
            windSpeed,
            windDirection,
            seaLevelPressure,
            time)

        Button(
            onClick = onUpdateButtonClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Update Weather")
        }
    }
}

/**
 * Application UI, in landscape mode
 */
@Composable
fun LandscapeWeatherUI(
    latitude: Float,
    longitude: Float,
    temperature: Float,
    windSpeed: Float,
    windDirection: Int,
    weathercode: Int,
    seaLevelPressure: Float,
    time: String,
    is_day: Int,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) { // input + button on left side
            CoordinatesCard(
                latitude.toString(),
                longitude.toString(),
                onLatitudeChange,
                onLongitudeChange
            )
            Button(onClick = onUpdateButtonClick, modifier = Modifier.padding(top = 8.dp)) {
                Text("Update Weather")
            }
        }
        Column(modifier = Modifier.weight(1f)) { // weather info on right side

            WeatherCard(temperature, windSpeed, windDirection, seaLevelPressure, time)
        }

    }
}


//@Preview(showBackground = true, name = "Portrait Mode")
//@Composable
//fun PortraitPreview() {
//    PortraitWeatherUI(
//        latitude = 38.72f,
//        longitude = -9.13f,
//        temperature = 25.5f,
//        windSpeed = 12.0f,
//        windDirection = 45,
//        weathercode = 2,
//        seaLevelPressure = 1013.2f,
//        time = "2026-04-21T10:30",
//        is_day = 1,
//        onLatitudeChange = {},
//        onLongitudeChange = {},
//        onUpdateButtonClick = {}
//    )
//}