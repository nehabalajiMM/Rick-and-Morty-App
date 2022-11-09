package com.example.rickandmortyapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapp.ui.CharacterDetails
import com.example.rickandmortyapp.ui.characterList.CharacterListScreen
import com.example.rickandmortyapp.viewmodel.main.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            CharacterListScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screens.DetailsScreen.route + "/{character_name}/{character_image}/{character_gender}/{character_species}",
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
            CharacterDetails(it.arguments?.getString("character_image").toString(), it.arguments?.getString("character_name").toString(), it.arguments?.getString("character_gender").toString(), it.arguments?.getString("character_species").toString(), navController)
        }
    }
}
