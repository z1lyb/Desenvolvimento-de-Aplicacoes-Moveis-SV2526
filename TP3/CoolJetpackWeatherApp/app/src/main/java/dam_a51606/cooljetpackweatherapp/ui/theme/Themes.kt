package dam_a51606.cooljetpackweatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightTheme = lightColorScheme(
    // App light mode
    primary = light_blue, // buttons + accents
    surfaceVariant = white, // card colors
    onPrimary = black, // button text
    onSurfaceVariant = black, // card text
    background = blue // screen background
)

val darkTheme = darkColorScheme(
    // App dark mode
    primary = dark_blue,
    surfaceVariant = grey,
    onPrimary = white,
    onSurfaceVariant = white,
    background = darker_blue
)


@Composable
fun AppTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // App theme definition
    val colorScheme = if (isDarkTheme) darkTheme else lightTheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}