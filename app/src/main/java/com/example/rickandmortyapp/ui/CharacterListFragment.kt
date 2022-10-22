package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterListAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private lateinit var characterListAdapter: CharacterListAdapter

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
            override fun onClick(character: CharacterResult, view: View) {
                val action = CharacterListFragmentDirections.actionCharacterListFragmentToDetailsFragment(character)
                Navigation.findNavController(view).navigate(action)
            }
        }

        characterListAdapter = CharacterListAdapter()
        characterListAdapter.setItemClickListener(clickListener)
        binding?.characterListAdapter = characterListAdapter

        lifecycleScope.launch {
            characterListAdapter.loadStateFlow.collectLatest { loadStates ->
                binding?.progressBar?.isVisible = loadStates.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.charactersFlow.collectLatest {
                characterListAdapter.submitData(it)
            }
        }

        binding?.svSearchCharacter?.clearFocus()
        binding?.svSearchCharacter?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getSearchedCharacters(newText.toString())
                characterListAdapter.submitData(lifecycle, PagingData.empty())
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    viewModel.searchedCharactersFlow?.collectLatest {
                        characterListAdapter.submitData(it)
                    }
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
