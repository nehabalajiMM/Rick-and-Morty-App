package com.example.rickandmortyapp.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    lateinit var charactersFlow: Flow<PagingData<CharacterResult>>
        private set

    lateinit var searchedCharactersFlow: Flow<PagingData<CharacterResult>>
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        charactersFlow = repository.getCharacters().cachedIn(viewModelScope)
    }

    fun getSearchedCharacters(name: String) {
        Log.v("SEARCH", name)
        searchedCharactersFlow = repository.getCharacters(name).cachedIn(viewModelScope)
    }
}
