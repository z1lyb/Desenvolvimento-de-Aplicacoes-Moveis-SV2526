package dam_a51606.cooljetpackweatherapp.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // 1. Estados locais para os TextFields (Buffers)
    var latInput by remember { mutableStateOf(weatherUIState.latitude.toString()) }
    var lonInput by remember { mutableStateOf(weatherUIState.longitude.toString()) }

    // 2. Sincronizar se o ViewModel mudar (ex: carregamento inicial)
    LaunchedEffect(weatherUIState.latitude, weatherUIState.longitude) {
        latInput = weatherUIState.latitude.toString()
        lonInput = weatherUIState.longitude.toString()
    }

//    val latitude = weatherUIState.latitude
//    val longitude = weatherUIState.longitude
    val temperature = weatherUIState.temperature
    val windSpeed = weatherUIState.windspeed
    val windDirection = weatherUIState.winddirection
    val weathercode = weatherUIState.weathercode
    val seaLevelPressure = weatherUIState.seaLevelPressure
    val time = weatherUIState.time
    val is_day = weatherUIState.is_day

    val configuration = LocalConfiguration.current

    // Função unificada para atualizar
//    val updateWeather = {
//
//    }

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeWeatherUI(
            latInput,
            lonInput,
            temperature,
            windSpeed,
            windDirection,
            weathercode,
            seaLevelPressure,
            time,
            is_day,
            onLatitudeChange = { latInput = it },
            onLongitudeChange = { lonInput = it },
            onUpdateButtonClick = {
                val lat = latInput.toFloatOrNull()
                val lon = lonInput.toFloatOrNull()
                if (lat != null && lon != null) weatherViewModel.fetchWeather(lat, lon)
            }
        )
    } else {
        PortraitWeatherUI(
            latInput,
            lonInput,
            temperature,
            windSpeed,
            windDirection,
            weathercode,
            seaLevelPressure,
            time,
            is_day,
            onLatitudeChange = { latInput = it },
            onLongitudeChange = { lonInput = it },
            onUpdateButtonClick = {
                val lat = latInput.toFloatOrNull()
                val lon = lonInput.toFloatOrNull()
                if (lat != null && lon != null) weatherViewModel.fetchWeather(lat, lon)
            }
        )
    }
}

/**
 * Application UI, in portrait mode
 */
@Composable
fun PortraitWeatherUI(
    latitude: String,
    longitude: String,
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        WeatherIcon(
            weathercode = weathercode,
            is_day = is_day
        )

        CoordinatesCard(
            latitude,
            longitude,
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
    latitude: String,
    longitude: String,
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
        Column( // input + button on left side
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoordinatesCard(
                latitude,
                longitude,
                onLatitudeChange,
                onLongitudeChange
            )
            Button(
                onClick = onUpdateButtonClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Update Weather")
            }
        }
        Column( // weather info on right side
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherIcon(
                weathercode,
                is_day,
                modifier = Modifier.size(100.dp)
            )
            WeatherCard(
                temperature,
                windSpeed,
                windDirection,
                seaLevelPressure,
                time
            )
        }

    }
}