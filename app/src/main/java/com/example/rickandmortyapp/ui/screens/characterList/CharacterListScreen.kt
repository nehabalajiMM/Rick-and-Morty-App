package com.example.rickandmortyapp.ui.screens.characterList

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Text
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.screens.characterList.components.CharacterList
import com.example.rickandmortyapp.ui.screens.characterList.components.SearchCharacterField
import com.example.rickandmortyapp.ui.screens.characterList.viewmodel.CharacterListViewModel
import com.example.rickandmortyapp.ui.theme.AppFontTypography

@Composable
fun CharacterListScreen(
    navigate: (route: String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characterListFlow by viewModel.characterItemList.collectAsState()
    val characterList = characterListFlow.collectAsLazyPagingItems()
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.testTag("Start Screen")
    ) {
        Text(
            style = AppFontTypography.h1,
            text = stringResource(id = R.string.characters),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
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