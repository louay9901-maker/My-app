package com.wansawi.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// لوحة ألوان هادئة ومريحة للعين (أخضر مريخي + ألوان ترابية ناعمة)
private val LightColors = lightColorScheme(
    primary = Color(0xFF3E6B5A),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC2E9D4),
    onPrimaryContainer = Color(0xFF0A3524),
    secondary = Color(0xFF7C8B7E),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE3EAE0),
    onSecondaryContainer = Color(0xFF2C3A2E),
    tertiary = Color(0xFFB08A5E),
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFF8F7F2),
    onBackground = Color(0xFF1C1B18),
    surface = Color(0xFFFDFCF8),
    onSurface = Color(0xFF1C1B18),
    surfaceVariant = Color(0xFFE9EBE3),
    onSurfaceVariant = Color(0xFF4C554B),
    outline = Color(0xFF7B857A),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF97D5B5),
    onPrimary = Color(0xFF06301F),
    primaryContainer = Color(0xFF275240),
    onPrimaryContainer = Color(0xFFC2E9D4),
    secondary = Color(0xFFB6C8BA),
    onSecondary = Color(0xFF1D2A20),
    secondaryContainer = Color(0xFF33403A),
    onSecondaryContainer = Color(0xFFE3EAE0),
    tertiary = Color(0xFFE5C089),
    onTertiary = Color(0xFF412C0A),
    background = Color(0xFF131511),
    onBackground = Color(0xFFE4E3DD),
    surface = Color(0xFF191B18),
    onSurface = Color(0xFFE4E3DD),
    surfaceVariant = Color(0xFF3F4A42),
    onSurfaceVariant = Color(0xFFBCCAC0),
    outline = Color(0xFF879288),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun WansawiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
