package com.example.rickandmortyapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val Colors = lightColors(
    primary = holo_blue_light,
    primaryVariant = holo_blue_dark,
    onPrimary = black,
    secondary = blue_secondary,
    secondaryVariant = blue,
    onSecondary = black,
    error = red
)

@Composable
fun RickAndMortyAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = Colors,
        shapes = Shapes,
        typography = AppFontTypography,
        content = content
    )
}
