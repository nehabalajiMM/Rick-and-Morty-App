package com.example.rickandmortyapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.rickandmortyapp.ui.theme.holo_blue_light
import com.example.rickandmortyapp.ui.theme.holo_green_light

@Composable
fun CharacterDetails(name: String, gender: String, species: String, image: String, navController: NavController) {
    RickAndMortyAppTheme {
        Column {
            DisplayTopAppBar(name = name, navController)
            DisplayImage(url = image)
            DisplayText(stringId = R.string.character_name_detail, value = name, color = holo_blue_light, fontSize = 36.sp)
            DisplayText(stringId = R.string.character_gender_detail, value = gender, color = holo_green_light, fontSize = 24.sp)
            DisplayText(stringId = R.string.character_species_detail, value = species, color = holo_green_light, fontSize = 24.sp)
        }
    }
}

@Composable
fun DisplayTopAppBar(name: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = name,
                color = colorResource(id = R.color.blue_light)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = colorResource(id = R.color.blue_light)
                )
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun DisplayImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(id = R.drawable.rick_and_morty_placeholder),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun DisplayText(stringId: Int, value: String, fontSize: TextUnit, color: Color) {
    Text(
        stringResource(id = stringId, value),
        color = color,
        fontSize = fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
}
