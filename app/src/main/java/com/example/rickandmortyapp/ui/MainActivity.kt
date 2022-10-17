package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import com.example.rickandmortyapp.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCharacters()
        viewModel.characterResponse.observe(
            this,
            Observer {
                Log.d("RESPONSE", it.results.toString())
            }
        )
    }
}
