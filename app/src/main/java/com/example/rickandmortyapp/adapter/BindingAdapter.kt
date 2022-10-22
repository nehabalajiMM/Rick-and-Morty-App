package com.example.rickandmortyapp.adapter // ktlint-disable filename

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.rickandmortyapp.R

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(characterPhoto: String?) {
    this.load(characterPhoto) {
        transformations(RoundedCornersTransformation(30f))
        placeholder(R.drawable.rick_and_morty_placeholder)
    }
}

@BindingAdapter("loadImageFromUrlWithoutTransformation")
fun ImageView.loadImageWithoutTransformation(characterPhoto: String?) {
    this.load(characterPhoto) {
        placeholder(R.drawable.rick_and_morty_placeholder)
    }
}
