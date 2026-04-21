package dam_a51606.cooljetpackweatherapp.ui

data class WeatherUIState(
    val latitude: Float = 0.0f,
    val longitude: Float = 0.0f,
    val temperature: Float = 0.0f,
    val windspeed: Float = 0.0f,
    val winddirection: Int = 0, // º
    val weathercode: Int = 0,
    val seaLevelPressure: Float = 0.0f,
    val time: String = "",
    val is_day: Int = 1, // 1 day 0 night
)