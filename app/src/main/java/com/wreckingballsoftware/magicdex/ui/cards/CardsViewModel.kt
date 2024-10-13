package com.wreckingballsoftware.magicdex.ui.cards

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wreckingballsoftware.magicdex.data.repos.CardPagingSource
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenEvent
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenOneOffs
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

private const val CARD_PAGE_SIZE = 20

class CardsViewModel(
    private val pagingSource: CardPagingSource,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(CardsScreenState())
    }
    val cards = Pager(PagingConfig(pageSize = CARD_PAGE_SIZE)) {
        pagingSource
    }.flow
    private val _oneOffEvent = MutableSharedFlow<CardsScreenOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    fun onEvent(event: CardsScreenEvent) {
        when (event) {
            is CardsScreenEvent.ApiError -> state = state.copy(alertMessage = event.message)
            CardsScreenEvent.DismissDialog -> state = state.copy(alertMessage = null)
        }
    }
}
