package dam_a51606.cooljetpackweatherapp.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dam_a51606.cooljetpackweatherapp.data.WMO_WeatherCode
import dam_a51606.cooljetpackweatherapp.data.getWeatherCodeMap

@Composable
fun WeatherIcon(weathercode: Int, is_day: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val iconResId = getIconResource(context, weathercode, is_day)

    if (iconResId != 0) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather icon",
            modifier = modifier.size(120.dp)
        )
    }
}

fun getIconResource(context: Context, code: Int, is_day: Int): Int {
    val mapt = getWeatherCodeMap()
    val wCode = mapt[code]

    val timeOfDay = if (is_day == 1) "day" else "night"

    val wImage = when (wCode) {
        WMO_WeatherCode.CLEAR_SKY,
        WMO_WeatherCode.MAINLY_CLEAR,
        WMO_WeatherCode.PARTLY_CLOUDY -> "${wCode.image}$timeOfDay"

        else -> wCode?.image
    }

    return context.resources.getIdentifier(
        wImage,
        "drawable",
        context.packageName
    )
}