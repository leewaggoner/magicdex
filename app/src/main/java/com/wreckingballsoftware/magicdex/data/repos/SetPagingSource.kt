package com.wreckingballsoftware.magicdex.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wreckingballsoftware.magicdex.data.models.Set

class SetPagingSource(
    private val setRepo: SetRepo,
) : PagingSource<Int, Set>() {
    var query: String = ""
    override fun getRefreshKey(state: PagingState<Int, Set>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Set> {
        return try {
            val startIndex = params.key ?: 1
            val limit = params.loadSize
            val response = setRepo.getSets(
                startIndex = startIndex,
                limit = limit,
                query = query
            )

            LoadResult.Page(
                data = response.sets,
                prevKey = if (startIndex == 1) null else startIndex - 1,
                nextKey = if (response.sets.isEmpty()) null else startIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
