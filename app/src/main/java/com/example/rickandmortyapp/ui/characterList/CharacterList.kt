package com.example.rickandmortyapp.ui.characterList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import kotlinx.coroutines.Job

@Composable
fun CharacterListScreen(viewModel: MainViewModel) {
    val characterListFlow by viewModel.characterItemList.collectAsState()
    val characterList = characterListFlow.collectAsLazyPagingItems()
    MaterialTheme {
        Column {
            Text(
                text = stringResource(id = R.string.characters),
                fontSize = 36.sp,
                color = colorResource(id = R.color.blue_light),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                fontWeight = FontWeight.Bold
            )
            SearchCharacterField(viewModel)
            DisplayCharacterList(characterList)
        }
    }
}

var searchJob: Job? = null

@Composable
fun SearchCharacterField(viewModel: MainViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Search a character") },
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    )
//    viewModel.getSearchedCharacters(text.toString())
//    var coroutineScope = rememberCoroutineScope()
//    if (searchJob?.isActive != false) {
//        searchJob?.cancel()
//    }
//    searchJob = coroutineScope.launch {
//        delay(700)
//        while (searchJob?.isActive == true) {
//            viewModel.getSearchedCharacters(text.toString())
//        }
//    }
//    val searchedCharacterList by viewModel.searchedCharactersList.collectAsState()
//    val characters by searchedCharacterList.collectAsState(initial = emptyList())
//    DisplayCharacterList(characters = characters)
}

@Composable
fun DisplayCharacterList(characters: LazyPagingItems<CharacterResult>) {
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
            characters[it]?.let { i -> CharacterCard(characterResult = i) }
        }
    }
}

@Composable
fun CharacterCard(characterResult: CharacterResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.blue_light),
        shape = RoundedCornerShape(30F)
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
