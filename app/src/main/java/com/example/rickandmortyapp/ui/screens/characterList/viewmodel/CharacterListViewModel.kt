package com.example.rickandmortyapp.ui.screens.characterList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.model.Character
import com.example.rickandmortyapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _characterItemList = MutableStateFlow<Flow<PagingData<Character>>>(emptyFlow())
    val characterItemList = _characterItemList.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        _characterItemList.value = repository.getCharacters()
    }

    var searchJob: Job? = null
    fun getSearchedCharacters(name: String) {
        if (searchJob?.isActive != false) {
            searchJob?.cancel()
        }
        searchJob = viewModelScope.launch {
            delay(700)
            _characterItemList.value = repository.getCharacters(name)
        }
    }
}
