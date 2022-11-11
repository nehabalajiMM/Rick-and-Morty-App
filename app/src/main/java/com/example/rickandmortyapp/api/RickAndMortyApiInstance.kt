package com.example.rickandmortyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyApiInstance {

    const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RickAndMortyApi by lazy {
        retrofit.create(RickAndMortyApi::class.java)
    }
}
