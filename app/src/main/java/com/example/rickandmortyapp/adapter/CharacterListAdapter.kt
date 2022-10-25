package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.BR
import com.example.rickandmortyapp.databinding.CharacterListItemBinding
import com.example.rickandmortyapp.model.CharacterResult

class CharacterListAdapter : PagingDataAdapter<CharacterResult, CharacterListAdapter.CharacterListViewHolder>(diffCallback = DiffCallback) {

    private lateinit var clickListener: ClickListener

    object DiffCallback : DiffUtil.ItemCallback<CharacterResult>() {
        override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
            return oldItem == newItem
        }
    }

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
        val character = getItem(position)
        holder.bind(character!!)
    }

    interface ClickListener {
        fun onClick(character: CharacterResult, view: View)
    }

    fun setItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class CharacterListViewHolder(val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var characterStatusImage = binding.ivDeadAliveStatus

        fun bind(character: CharacterResult) {
            binding.setVariable(BR.character, character)
            binding.root.setOnClickListener {
                clickListener.onClick(character, it)
            }
        }
    }
}
