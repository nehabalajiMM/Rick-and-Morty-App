package com.example.rickandmortyapp.dataSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.api.RickAndMortyApi
import com.example.rickandmortyapp.model.Character
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val api: RickAndMortyApi,
    private val name: String? = null
) : PagingSource<Int, Character>() {

    private val startingPageIndex = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentCharacterList = params.key ?: startingPageIndex

            val response = if (name.isNullOrBlank()) {
                api.getCharacters(currentCharacterList)
            } else {
                api.getCharacters(currentCharacterList, name)
            }

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

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}
