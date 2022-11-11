package com.example.rickandmortyapp.ui.screens.characterDetails.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.rickandmortyapp.ui.theme.holo_blue_light
import com.example.rickandmortyapp.ui.theme.white

@Composable
fun TopAppBar(name: String, navigateUp: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = name,
                color = holo_blue_light
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = holo_blue_light
                )
            }
        },
        backgroundColor = white
    )
}