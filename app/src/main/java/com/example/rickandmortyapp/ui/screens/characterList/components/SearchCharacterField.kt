package com.example.rickandmortyapp.ui.screens.characterList.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchCharacterField(text: String, onChange: (String) -> Unit) {
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
                    onClick = { onChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = ""
                    )
                }
            }
        },
        onValueChange = onChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp).padding(bottom = 8.dp)
    )
}
