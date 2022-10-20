package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.BR
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterListItemBinding
import com.example.rickandmortyapp.model.Result

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private lateinit var clickListener: ClickListener

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
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
        holder.bind(character)
        if (character.status == "Dead") {
            holder.characterStatusImage.setColorFilter(ContextCompat.getColor(holder.characterStatusImage.context, R.color.red))
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface ClickListener {
        fun onClick(character: Result, view: View)
    }

    fun setItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class CharacterListViewHolder(val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var characterStatusImage = binding.ivDeadAliveStatus

        fun bind(character: Result) {
            binding.setVariable(BR.character, character)
            binding.root.setOnClickListener {
                clickListener.onClick(character, it)
            }
        }
    }
}
