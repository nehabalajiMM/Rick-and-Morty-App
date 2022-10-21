package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterListAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.model.CharacterResult
import com.example.rickandmortyapp.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel: MainViewModel by viewModels()

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
        val characterListAdapter = CharacterListAdapter()
        characterListAdapter.setItemClickListener(clickListener)
        binding?.characterListAdapter = characterListAdapter

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
