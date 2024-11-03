package com.wreckingballsoftware.magicdex.ui.carddetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wreckingballsoftware.magicdex.data.network.ApiResult
import com.wreckingballsoftware.magicdex.data.repos.CardRepo
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailEvents
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailState
import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val cardRepo: CardRepo,
    handle: SavedStateHandle,
) : ViewModel() {
    var state by mutableStateOf(CardDetailState())
        private set

    init {
        val cardId = handle.get<String>("cardId")
        cardId?.let { id ->
            viewModelScope.launch {
                state = when (val card = cardRepo.getCardById(id)) {
                    ApiResult.Loading -> state.copy(showProgress = true)
                    is ApiResult.Success -> state.copy(card = card.data, showProgress = false)
                    is ApiResult.Error -> state.copy(message = card.exception.message, showProgress = false)
                }
            }
        }
        state = state.copy(tabs = listOf(DetailTab.ABOUT, DetailTab.ART, DetailTab.MISC))
    }

    fun onEvent(event: CardDetailEvents) {
        when (event) {
            is CardDetailEvents.OnTabSelected -> onTabSelected(event.tab)
        }
    }

    private fun onTabSelected(tab: DetailTab) {
        state = state.copy(selected = tab)
    }
}
