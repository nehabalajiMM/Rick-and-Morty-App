package com.example.rickandmortyapp.ui.screens.characterDetails.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R

@Composable
fun ImageComponent(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(id = R.drawable.rick_and_morty_placeholder),
        modifier = Modifier
            .fillMaxWidth()
    )
}