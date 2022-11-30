package com.example.rickandmortyapp.repository

import androidx.paging.PagingData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test

class TestRepository {

    @Test
    fun testRepositoryMethod() {
        val sample1 = mockk<Repository>()
        every { sample1.getCharacters() } returns flowOf(PagingData.empty())
        Assert.assertFalse(sample1.getCharacters().equals(null))
    }
}