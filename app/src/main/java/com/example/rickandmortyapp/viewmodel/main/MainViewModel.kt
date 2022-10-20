package com.example.rickandmortyapp.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var _charactersFlow: Flow<PagingData<com.example.rickandmortyapp.model.Result>>
    val charactersFlow: Flow<PagingData<com.example.rickandmortyapp.model.Result>>
        get() = _charactersFlow

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        _charactersFlow = repository.getCharacters().cachedIn(viewModelScope)
    }
}
