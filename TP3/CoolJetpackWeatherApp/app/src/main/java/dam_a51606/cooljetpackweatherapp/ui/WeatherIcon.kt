package dam_a51606.cooljetpackweatherapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode
import dam_a51606.cooljetpackweatherapp.data.getWeatherCodeMap

@Composable
fun WeatherIcon(weathercode: Int, is_day: Int, modifier: Modifier = Modifier) {
    val iconResId = getIconResource(weathercode, is_day)

    if (iconResId != 0) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather icon",
            modifier = modifier.size(120.dp)
        )
    }
}

fun getIconResource(code: Int, is_day: Int): Int {
    val mapt = getWeatherCodeMap()
    val wCode = mapt[code]

    val wImage = when (wCode) {
        WMO_WeatherCode.CLEAR_SKY,
        WMO_WeatherCode.MAINLY_CLEAR,
        WMO_WeatherCode.PARTLY_CLOUDY -> if (is_day == 0) wCode.image else wCode.image

        else -> wCode?.image
    }

    return 0
}