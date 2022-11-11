package com.example.rickandmortyapp.ui.screens.characterList.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.theme.AppFontTypography

@Composable
fun TextComponent() {
    Text(
        style = AppFontTypography.h1,
        text = stringResource(id = R.string.characters),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}