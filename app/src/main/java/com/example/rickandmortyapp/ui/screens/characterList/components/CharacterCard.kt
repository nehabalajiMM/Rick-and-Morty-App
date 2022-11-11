package com.example.rickandmortyapp.ui.screens.characterList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.model.Character
import com.example.rickandmortyapp.ui.Screens
import com.example.rickandmortyapp.ui.theme.AppFontTypography
import com.example.rickandmortyapp.ui.theme.Shapes
import com.example.rickandmortyapp.ui.theme.holo_blue_light
import java.net.URLEncoder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(characterResult: Character, navigate: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = holo_blue_light,
        shape = Shapes.medium,
        onClick = {
            navigate(
                Screens.DETAILS_SCREEN.name.plus(
                    "/${characterResult.name}/${characterResult.gender}/${characterResult.species}/${
                    URLEncoder.encode(
                        characterResult.image
                    )
                    }"
                )
            )
        }
    ) {
        Column {
            AsyncImage(
                model = characterResult.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.rick_and_morty_placeholder),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clip(Shapes.medium)
            )
            Text(
                text = characterResult.name,
                style = AppFontTypography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 8.dp).padding(horizontal = 8.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = if (characterResult.status == "Alive") R.drawable.circle_green else R.drawable.circle_red),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${characterResult.status} - ${characterResult.species}",
                    style = AppFontTypography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
