package com.example.rickandmortyapp.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _characterItemList = MutableStateFlow<Flow<PagingData<CharacterResult>>>(emptyFlow())
    val characterItemList = _characterItemList.asStateFlow()

    private val _searchedCharactersList = MutableStateFlow<Flow<PagingData<CharacterResult>>>(emptyFlow())
    val searchedCharactersList = _searchedCharactersList.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        _characterItemList.value = repository.getCharacters()
        Log.v("Characters", _characterItemList.value.asLiveData().value.toString())
    }

    fun getSearchedCharacters(name: String) {
        _searchedCharactersList.value = repository.getCharacters(name)
    }
}
