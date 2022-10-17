package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.api.RetrofitInstance
import com.example.rickandmortyapp.model.RickAndMortyCharacter

class Repository {

    suspend fun getCharacters(): RickAndMortyCharacter {
        return RetrofitInstance.api.getCharacters()
    }
}
