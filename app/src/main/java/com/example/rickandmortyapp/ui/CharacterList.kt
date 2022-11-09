@file:OptIn(ExperimentalMaterialApi::class)

package com.example.rickandmortyapp.ui.characterList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.rickandmortyapp.ui.theme.holo_blue_light
import com.example.rickandmortyapp.util.Screens
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import java.net.URLEncoder

@Composable
fun CharacterListScreen(viewModel: MainViewModel, navController: NavController) {
    val characterListFlow by viewModel.characterItemList.collectAsState()
    val characterList = characterListFlow.collectAsLazyPagingItems()
    RickAndMortyAppTheme {
        Column {
            Text(
                text = stringResource(id = R.string.characters),
                fontSize = 36.sp,
                color = holo_blue_light,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                fontWeight = FontWeight.Bold
            )
            SearchCharacterField {
                viewModel.getSearchedCharacters(it)
            }
            DisplayCharacterList(characterList, navController)
        }
    }
}


@Composable
fun SearchCharacterField(searchCharacter: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Search a character") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        trailingIcon = {
            if (text != "") {
                IconButton(
                    onClick = {
                        text = ""
                        searchCharacter(text)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = ""
                    )
                }
            }
        },
        onValueChange = {
            text = it
            searchCharacter(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun DisplayCharacterList(characters: LazyPagingItems<CharacterResult>, navController: NavController) {
    Log.v("Characters", characters.toString())
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            characters.itemCount
        ) {
            characters[it]?.let { i -> CharacterCard(characterResult = i, navController) }
        }
    }
}

@Composable
fun CharacterCard(characterResult: CharacterResult, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.blue_light),
        shape = RoundedCornerShape(30F),
        onClick = {
            Log.v("ROUTE", "${Screens.DetailsScreen.route}/$characterResult")
            navController.navigate(Screens.DetailsScreen.route.plus("/${characterResult.name}/${characterResult.gender}/${characterResult.species}/${URLEncoder.encode(characterResult.image)}"))
        }
    ) {
        Column {
            AsyncImage(
                model = characterResult.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.rick_and_morty_placeholder),
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30F))
            )
            Text(
                text = characterResult.name,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = if (characterResult.status == "Alive") R.drawable.circle_green else R.drawable.circle_red),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    text = "${characterResult.status} - ${characterResult.species}",
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
