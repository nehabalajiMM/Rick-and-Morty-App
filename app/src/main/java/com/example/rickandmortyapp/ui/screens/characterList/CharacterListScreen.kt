package com.example.rickandmortyapp.ui.screens.characterList

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapp.ui.screens.characterList.components.CharacterList
import com.example.rickandmortyapp.ui.screens.characterList.components.SearchCharacterField
import com.example.rickandmortyapp.ui.screens.characterList.components.TextComponent
import com.example.rickandmortyapp.ui.screens.characterList.viewmodel.CharacterListViewModel

@Composable
fun CharacterListScreen(
    navigate: (route: String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characterListFlow by viewModel.characterItemList.collectAsState()
    val characterList = characterListFlow.collectAsLazyPagingItems()
    var text by rememberSaveable { mutableStateOf("") }
    Column {
        TextComponent()
        SearchCharacterField(
            text = text,
            onChange = {
                text = it
                viewModel.getSearchedCharacters(text)
            }
        )
        CharacterList(characterList, navigate)
    }
}