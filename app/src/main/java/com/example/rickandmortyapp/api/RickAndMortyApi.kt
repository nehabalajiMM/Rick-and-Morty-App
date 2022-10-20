package com.example.rickandmortyapp.api

import com.example.rickandmortyapp.model.RickAndMortyCharacter
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character/?page=2")
    suspend fun getCharacters(): RickAndMortyCharacter
}
