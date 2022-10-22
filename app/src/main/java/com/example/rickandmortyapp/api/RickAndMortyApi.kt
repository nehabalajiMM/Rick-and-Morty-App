package com.example.rickandmortyapp.api

import com.example.rickandmortyapp.model.RickAndMortyCharacter
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): RickAndMortyCharacter
}
