package com.west.cstack.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

private val DarkColors = darkColorScheme(
    primary = Color(0xFF1EB980),
    secondary = Color(0xFF045D56)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF00695C),
    secondary = Color(0xFF004D40)
)

@Composable
fun CStackTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
