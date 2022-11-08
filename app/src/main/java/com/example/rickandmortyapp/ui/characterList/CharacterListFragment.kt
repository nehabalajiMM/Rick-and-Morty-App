package com.example.rickandmortyapp.ui.characterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel: MainViewModel by viewModels()

//    private lateinit var characterListAdapter: CharacterListAdapter

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

        binding?.composeViewCharacterList?.setContent {
            CharacterListScreen(viewModel = viewModel)
        }

//        val clickListener = object : CharacterListAdapter.ClickListene {
//            override fun onClick(character: CharacterResult, view: View) {
//                val action = CharacterListFragmentDirections.actionCharacterListFragmentToDetailsFragment(character)
//                Navigation.findNavController(view).navigate(action)
//            }
//        }
//
//        binding?.rvCharacterList?.addItemDecoration(GridSpacingItemDecoration(3, 30))
//        characterListAdapter = CharacterListAdapter()
//        characterListAdapter.setItemClickListener(clickListener)
//        binding?.characterListAdapter = characterListAdapter
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            characterListAdapter.loadStateFlow.collectLatest { loadStates ->
//                binding?.progressBar?.isVisible = loadStates.refresh is LoadState.Loading
//                binding?.tvErrorMessage?.isVisible = loadStates.refresh is LoadState.Error
//            }
//        }
//
//
//
//        var searchJob: Job? = null
//        binding?.tfSearchCharacter?.editText?.doOnTextChanged { text, _, _, _ ->
//            if (searchJob?.isActive != false) {
//                searchJob?.cancel()
//            }
//            searchJob = viewLifecycleOwner.lifecycleScope.launch {
//                delay(700)
//                while (searchJob?.isActive == true) {
//                    characterListAdapter.submitData(PagingData.empty())
//                    viewModel.getSearchedCharacters(text.toString())
//                    viewModel.searchedCharactersFlow.collectLatest {
//                        characterListAdapter.submitData(it)
//                    }
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
