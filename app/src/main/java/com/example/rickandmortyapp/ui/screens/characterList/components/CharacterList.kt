package com.example.rickandmortyapp.ui.screens.characterList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortyapp.model.Character

@Composable
fun CharacterList(
    characters: LazyPagingItems<Character>,
    navigate: (route: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            characters.itemCount
        ) {
            characters[it]?.let { i -> CharacterCard(characterResult = i, navigate) }
        }
    }
}