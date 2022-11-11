package com.example.rickandmortyapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.api.RickAndMortyApi
import com.example.rickandmortyapp.dataSource.CharacterDataSource
import com.example.rickandmortyapp.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: RickAndMortyApi
) {
    fun getCharacters(name: String? = null): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharacterDataSource(api, name) }
    ).flow
}
