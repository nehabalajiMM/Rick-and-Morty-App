package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.adapter.CharacterListAdapter
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import com.example.rickandmortyapp.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterListAdapter = CharacterListAdapter()
        binding.characterListAdapter = characterListAdapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCharacters()
        viewModel.characterResponse.observe(
            this,
            Observer {
                characterListAdapter.differ.submitList(it.results)
                Log.d("RESPONSE", it.results.toString())
            }
        )
    }
}
