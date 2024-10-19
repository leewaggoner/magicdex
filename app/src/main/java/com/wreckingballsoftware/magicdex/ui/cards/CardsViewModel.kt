package com.wreckingballsoftware.magicdex.ui.cards

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wreckingballsoftware.magicdex.data.repos.CardPagingSource
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenEvent
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenOneOffs
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenState
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

private const val CARD_PAGE_SIZE = 20

class CardsViewModel(
    private val pagingSource: CardPagingSource,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(CardsScreenState())
    }

    private val _search = MutableStateFlow("")
    private val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val cards = search.debounce(timeoutMillis = 300).flatMapLatest { query ->
        Pager(PagingConfig(pageSize = CARD_PAGE_SIZE)) {
            pagingSource.query = query
            pagingSource
        }.flow
    }

    private val _oneOffEvent = MutableSharedFlow<CardsScreenOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    fun onEvent(event: CardsScreenEvent) {
        when (event) {
            is CardsScreenEvent.ApiError -> state = state.copy(alertMessage = event.message)
            CardsScreenEvent.DismissDialog -> state = state.copy(alertMessage = null)
            is CardsScreenEvent.Search -> onSearch(event.query)
            is CardsScreenEvent.OnCardSelected -> onCardSelected(event.cardId)
        }
    }

    private fun onSearch(query: String) {
        _search.value = query
    }

    private fun onCardSelected(cardId: String) {
        if (cardId.isNotEmpty()) {
            viewModelScope.launch {
                _oneOffEvent.emit(CardsScreenOneOffs.NavigateToCardDetail(cardId))
            }
        }
    }
}
