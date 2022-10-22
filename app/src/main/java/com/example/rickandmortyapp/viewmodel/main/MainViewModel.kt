package com.example.rickandmortyapp.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    lateinit var charactersFlow: Flow<PagingData<com.example.rickandmortyapp.model.CharacterResult>>
        private set

    lateinit var searchedCharactersFlow: Flow<PagingData<com.example.rickandmortyapp.model.CharacterResult>>
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        charactersFlow = repository.getCharacters().cachedIn(viewModelScope)
    }

    var job: Job? = null
    fun getSearchedCharacters(name: String) {
        job = viewModelScope.launch {
            job?.cancel()
            val response = repository.getCharacters(name)
            searchedCharactersFlow = response
        }
    }
}
