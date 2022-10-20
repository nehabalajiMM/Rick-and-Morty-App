package com.example.rickandmortyapp.adapter // ktlint-disable filename

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(characterPhoto: String?) {
    this.load(characterPhoto) {
        transformations(RoundedCornersTransformation(30f))
    }
}
