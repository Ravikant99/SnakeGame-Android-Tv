package com.example.growingsnake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

/**
 * Theme for the Snake Game optimized for Android TV.
 * Uses high-contrast colors for better visibility on TV screens.
 * Always applies dark theme for optimal TV viewing experience.
 *
 * @param isInDarkTheme Whether to use dark theme (ignored, always uses dark)
 * @param content The composable content to theme
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun GrowingSnakeTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = darkColorScheme(
        primary = BrightCyan,
        onPrimary = DeepBlack,
        secondary = VividBlue,
        onSecondary = BrightWhite,
        tertiary = BrightYellow,
        onTertiary = DeepBlack,
        background = DarkGray,
        onBackground = BrightWhite,
        surface = DeepBlack,
        onSurface = BrightWhite,
        surfaceVariant = Color(0xFF1E1E1E),
        onSurfaceVariant = BrightWhite
    )
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}