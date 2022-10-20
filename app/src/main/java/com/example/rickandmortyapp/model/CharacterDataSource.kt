package com.example.rickandmortyapp.model

import android.net.Uri
import androidx.paging.PagingSource
import com.example.rickandmortyapp.api.RickAndMortyApi
import javax.inject.Inject

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class CharacterDataSource @Inject constructor(
    private val api: RickAndMortyApi
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentCharacterList = params.key ?: CHARACTER_STARTING_PAGE_INDEX
            val response = api.getCharacters(currentCharacterList)

            val data = response.results
            var nextPageNumber: Int? = null
            if (response?.info?.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
