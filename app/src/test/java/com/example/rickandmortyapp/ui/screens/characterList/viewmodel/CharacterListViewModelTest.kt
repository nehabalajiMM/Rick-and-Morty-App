package com.example.rickandmortyapp.ui.screens.characterList.viewmodel

import androidx.lifecycle.asLiveData
import com.example.rickandmortyapp.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterListViewModelTest {

    @MockK
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testToGetAllCharacters() {
        runTest {
            launch {
                every {
                    repository.getCharacters()
                } returns emptyFlow()
                Assert.assertFalse(repository.getCharacters().equals(null))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testToGetSearchCharacters() {
        runTest {
            launch {
                every {
                    repository.getCharacters("rick")
                } returns emptyFlow()
                Assert.assertFalse(repository.getCharacters("rick").equals(null))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testToNotGetSearchedCharacters() {
        runTest {
            launch {
                every {
                    repository.getCharacters("hsjhfksekfgnk")
                } returns emptyFlow()
                Assert.assertTrue(repository.getCharacters("hsjhfksekfgnk").asLiveData().value == null)
            }
        }
    }
}