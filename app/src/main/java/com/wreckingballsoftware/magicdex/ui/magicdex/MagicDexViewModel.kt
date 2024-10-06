package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wreckingballsoftware.magicdex.data.repos.CardPagingSource
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexEvent
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexOneOffs
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

private const val CARD_PAGE_SIZE = 20

class MagicDexViewModel(
    private val pagingSource: CardPagingSource,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MagicDexState())
    }
    val cards = Pager(PagingConfig(pageSize = CARD_PAGE_SIZE)) {
        pagingSource
    }.flow
    private val _oneOffEvent = MutableSharedFlow<MagicDexOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    fun onEvent(event: MagicDexEvent) {
        when (event) {
            is MagicDexEvent.ApiError -> state = state.copy(alertMessage = event.message)
            MagicDexEvent.DismissDialog -> state = state.copy(alertMessage = null)
        }
    }
}
