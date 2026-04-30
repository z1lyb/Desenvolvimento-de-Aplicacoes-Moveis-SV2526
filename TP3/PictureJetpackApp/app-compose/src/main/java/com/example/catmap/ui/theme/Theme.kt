package com.example.catmap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colors from app-xml
val Primary = Color(0xFFD64C2B)
val PrimaryDark = Color(0xFFB03A1F)
val Secondary = Color(0xFFF5A623)
val Background = Color(0xFFF8F4F0)
val Surface = Color(0xFFFFFFFF)
val HeartRed = Color(0xFFE91E63)
val OnSurface = Color(0xFF1C1B1F)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Secondary,
    onSecondary = Color.White,
    background = Color(0xFF121212), // Dark mode background
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Secondary,
    onSecondary = Color.White,
    background = Background,
    surface = Surface,
    onSurface = OnSurface
)

@Composable
fun CatMapTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
