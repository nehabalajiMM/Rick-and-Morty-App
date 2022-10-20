package com.example.rickandmortyapp.adapter // ktlint-disable filename

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(characterPhoto: String?) {
    this.load(characterPhoto) {
        transformations(RoundedCornersTransformation(30f))
    }
}

@BindingAdapter("loadImageFromUrlWithoutTransformation")
fun ImageView.loadImageWithoutTransformation(characterPhoto: String?) {
    this.load(characterPhoto)
}

@BindingAdapter("setName")
fun TextView.setName(name: String) {
    this.text = "Name: $name"
}

@BindingAdapter("setGender")
fun TextView.setGender(gender: String) {
    this.text = "Gender: $gender"
}

@BindingAdapter("setSpecies")
fun TextView.setSpecies(species: String) {
    this.text = "Species: $species"
}
