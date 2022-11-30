package com.example.rickandmortyapp.ui.screens.characterList

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListScreenTest {

    private lateinit var navController: NavController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithTag("Start Screen")
            .assertIsDisplayed()
    }

    @Test
    fun testIfTitleExists() {
        composeTestRule.onNode(hasText("Characters")).assertIsDisplayed()
    }

    @Test
    fun testIfSearchTextFieldWorks() {
        val searchText = "rick"
        composeTestRule.onNodeWithTag("searchTextFieldTag").performTextInput(searchText)
        composeTestRule.onNodeWithTag("searchTextFieldTag").assert(hasText(searchText))
    }
}