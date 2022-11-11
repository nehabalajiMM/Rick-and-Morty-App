package com.example.rickandmortyapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapp.ui.screens.characterDetails.CharacterDetailsScreen
import com.example.rickandmortyapp.ui.screens.characterList.CharacterListScreen
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MAIN_SCREEN.name) {
        composable(route = Screens.MAIN_SCREEN.name) {
            CharacterListScreen(navigate = {
                navController.navigate(it)
            })
        }
        composable(
            route = Screens.DETAILS_SCREEN.name + "/{character_name}/{character_image}/{character_gender}/{character_species}",
            arguments = listOf(
                navArgument("character_name") {
                    type = NavType.StringType
                },
                navArgument("character_image") {
                    type = NavType.StringType
                },
                navArgument("character_gender") {
                    type = NavType.StringType
                },
                navArgument("character_species") {
                    type = NavType.StringType
                }
            )
        ) {
            CharacterDetailsScreen(
                it.arguments?.getString("character_image").toString(),
                it.arguments?.getString("character_name").toString(),
                it.arguments?.getString("character_gender").toString(),
                it.arguments?.getString("character_species").toString()
            ) {
                navController.navigateUp()
            }
        }
    }
}

enum class Screens {
    MAIN_SCREEN, DETAILS_SCREEN
}

