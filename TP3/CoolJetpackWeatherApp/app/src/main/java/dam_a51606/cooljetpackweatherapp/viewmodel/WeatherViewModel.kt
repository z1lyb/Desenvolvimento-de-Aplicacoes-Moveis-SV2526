package dam_a51606.cooljetpackweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode
import dam_a51606.cooljetpackweatherapp.data.WeatherAPIClient
import dam_a51606.cooljetpackweatherapp.data.getWeatherCodeMap
import dam_a51606.cooljetpackweatherapp.ui.WeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUIState())
    val uiState: StateFlow<WeatherUIState> = _uiState.asStateFlow()

    init {
        fetchWeather(_uiState.value.latitude, _uiState.value.longitude)
    }

    // Update latitude and longitude values from those inserted in the UI
    fun updateLatitude(latitude: Float) {
        _uiState.update { it.copy(latitude = latitude) }
    }

    fun updateLongitude(longitude: Float) {
        _uiState.update { it.copy(longitude = longitude) }
    }


    // Fetch data from the API
    fun fetchWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            _uiState.update { it.copy(latitude = latitude, longitude = longitude) }

            val currentState = _uiState.value
            val result = WeatherAPIClient.getWeather(
                latitude,
                longitude
            )

            if (result != null) {
                _uiState.update {
                    it.copy(
                        temperature = result.current_weather.temperature,
                        windspeed = result.current_weather.windspeed,
                        winddirection = result.current_weather.winddirection,
                        weathercode = result.current_weather.weathercode,
                        seaLevelPressure = result.hourly.pressure_msl[0].toFloat(),
                        time = result.current_weather.time,
                        is_day = result.current_weather.is_day,
                    )
                }
            } else Log.e("WEATHER_DEBUG", "O resultado da API foi NULL")
        }
    }
}