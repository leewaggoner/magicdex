package com.wreckingballsoftware.magicdex.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wreckingballsoftware.magicdex.data.models.Card

class CardPagingSource(
    private val cardRepo: CardRepo,
) : PagingSource<Int, Card>() {
    var query: String = ""
    override fun getRefreshKey(state: PagingState<Int, Card>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Card> {
        return try {
            val startIndex = params.key ?: 1
            val limit = params.loadSize
            val response = cardRepo.getCards(
                startIndex = startIndex,
                limit = limit,
                query = query
            )

            LoadResult.Page(
                data = response.cards,
                prevKey = if (startIndex == 1) null else startIndex - 1,
                nextKey = if (response.cards.isEmpty()) null else startIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}