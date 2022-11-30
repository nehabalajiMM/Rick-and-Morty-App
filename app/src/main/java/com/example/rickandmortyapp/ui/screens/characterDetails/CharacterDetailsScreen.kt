package com.example.rickandmortyapp.ui.screens.characterDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.screens.characterDetails.components.ImageComponent
import com.example.rickandmortyapp.ui.screens.characterDetails.components.TextComponent
import com.example.rickandmortyapp.ui.screens.characterDetails.components.TopAppBar
import com.example.rickandmortyapp.ui.theme.AppFontTypography

@Composable
fun CharacterDetailsScreen(
    gender: String,
    name: String,
    species: String,
    image: String,
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .testTag("Details Screen")
    ) {
        TopAppBar(name = name, navigateUp)
        ImageComponent(url = image)
        TextComponent(
            text = stringResource(id = R.string.character_name_detail, name),
            style = AppFontTypography.h2
        )
        TextComponent(
            text = stringResource(id = R.string.character_gender_detail, gender),
            style = AppFontTypography.body2
        )
        TextComponent(
            text = stringResource(id = R.string.character_species_detail, species),
            style = AppFontTypography.body2
        )
    }
}




