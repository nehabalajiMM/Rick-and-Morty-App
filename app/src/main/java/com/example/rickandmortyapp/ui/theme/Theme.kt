package com.example.rickandmortyapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

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
        content = {
            ProvideTextStyle(
                value = TextStyle(color = com.example.rickandmortyapp.ui.theme.text_color_grey),
                content = content
            )
        }
    )
}
