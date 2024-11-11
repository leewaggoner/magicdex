package com.wreckingballsoftware.magicdex.ui.sets

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.wreckingballsoftware.magicdex.data.repos.SetPagingSource
import com.wreckingballsoftware.magicdex.ui.sets.models.SetsScreenEvent
import com.wreckingballsoftware.magicdex.ui.sets.models.SetsScreenOneOffs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val SET_PAGE_SIZE = 50

class SetsViewModel(
    private val pagingSource: SetPagingSource,
    handle: SavedStateHandle,
) : ViewModel() {
    private val _oneOffEvent = MutableSharedFlow<SetsScreenOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    private val _search = MutableStateFlow("")
    private val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val sets = search.debounce(timeoutMillis = 300).flatMapLatest { searchQuery ->
        Pager(PagingConfig(pageSize = SET_PAGE_SIZE)) {
            pagingSource.apply {
                query = searchQuery
            }
        }.flow
    }.cachedIn(scope = viewModelScope)

    fun onEvent(event: SetsScreenEvent) {
        when (event) {
            is SetsScreenEvent.ApiError -> onError(event.message)
            is SetsScreenEvent.Search -> onSearch(event.query)
        }
    }

    private fun onError(message: String) {
        viewModelScope.launch {
            _oneOffEvent.emit(SetsScreenOneOffs.ShowError(message))
        }
    }

    private fun onSearch(query: String) {
        _search.value = query
    }
}
