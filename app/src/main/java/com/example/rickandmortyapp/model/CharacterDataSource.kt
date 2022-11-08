package com.example.rickandmortyapp.model

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.api.RickAndMortyApi
import javax.inject.Inject

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class CharacterDataSource @Inject constructor(
    private val api: RickAndMortyApi,
    private val name: String? = null
) : PagingSource<Int, CharacterResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        return try {
            val currentCharacterList = params.key ?: CHARACTER_STARTING_PAGE_INDEX

            val response = if (name.isNullOrEmpty()) {
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

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return state.anchorPosition
    }
}
