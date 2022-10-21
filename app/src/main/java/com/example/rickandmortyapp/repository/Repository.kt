package com.example.rickandmortyapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.api.RickAndMortyApi
import com.example.rickandmortyapp.model.CharacterDataSource
import com.example.rickandmortyapp.model.CharacterResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RickAndMortyApi
) {
    fun getCharacters(): Flow<PagingData<CharacterResult>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharacterDataSource(api) }
    ).flow
}
