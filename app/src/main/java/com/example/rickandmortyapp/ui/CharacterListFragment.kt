package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterListAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.model.Result
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import com.example.rickandmortyapp.viewmodel.main.MainViewModelFactory

class CharacterListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterListBinding.bind(view)

        val clickListener = object : CharacterListAdapter.ClickListener {
            override fun onClick(character: Result, view: View) {
                val action = CharacterListFragmentDirections.actionCharacterListFragmentToDetailsFragment(character)
                Navigation.findNavController(view).navigate(action)
            }
        }
        val characterListAdapter = CharacterListAdapter()
        characterListAdapter.setItemClickListener(clickListener)
        binding?.characterListAdapter = characterListAdapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCharacters()
        viewModel.characterResponse.observe(
            viewLifecycleOwner,
            Observer {
                characterListAdapter.differ.submitList(it.results)
                Log.d("RESPONSE", it.results.toString())
            }
        )
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
