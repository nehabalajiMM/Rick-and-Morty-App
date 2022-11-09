package com.example.rickandmortyapp.util

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object DetailsScreen : Screens("details_screen")
}
