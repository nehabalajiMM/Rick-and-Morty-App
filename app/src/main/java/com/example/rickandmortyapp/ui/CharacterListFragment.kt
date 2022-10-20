package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterListAdapter
import com.example.rickandmortyapp.api.RetrofitInstance
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.model.Result
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import com.example.rickandmortyapp.viewmodel.main.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest

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

        val repository = Repository(RetrofitInstance.api)
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.charactersFlow.collectLatest {
                characterListAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
