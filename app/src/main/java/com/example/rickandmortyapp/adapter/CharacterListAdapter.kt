package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterListItemBinding
import com.example.rickandmortyapp.model.Result

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.characterName.text = character.name
        holder.characterStatus.text = character.species + " - " + character.status
        holder.characterImage.load(character.image) {
            transformations(RoundedCornersTransformation(30f))
        }
        if (character.status == "Dead") {
            holder.characterStatusImage.load(R.drawable.circle_red)
        } else {
            holder.characterStatusImage.load(R.drawable.circle_green)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class CharacterListViewHolder(binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val characterImage = binding.ivCharacterImage
        val characterName = binding.tvCharacterName
        val characterStatus = binding.tvStatus
        val characterStatusImage = binding.ivDeadAliveStatus
    }
}
