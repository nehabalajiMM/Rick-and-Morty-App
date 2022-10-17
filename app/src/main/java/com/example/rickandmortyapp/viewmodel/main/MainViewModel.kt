package com.example.rickandmortyapp.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.model.RickAndMortyCharacter
import com.example.rickandmortyapp.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val characterResponse: MutableLiveData<RickAndMortyCharacter> = MutableLiveData()

    fun getCharacters() {
        viewModelScope.launch {
            val response = repository.getCharacters()
            characterResponse.value = response
        }
    }
}
