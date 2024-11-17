package com.wreckingballsoftware.magicdex.ui.carddetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wreckingballsoftware.magicdex.domain.GetCardDataUseCase
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailEvents
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailState
import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val getCardDataUseCase: GetCardDataUseCase,
    handle: SavedStateHandle,
) : ViewModel() {
    var state by mutableStateOf(CardDetailState())
        private set

    init {
        val cardId = handle.get<String>("cardId")
        cardId?.let { id ->
            viewModelScope.launch {
                val error = getCardDataUseCase.getCardDataById(id)
                if (error != null) {
                    state = state.copy(message = error, showProgress = false)
                } else {
                    state = state.copy(
                        aboutData = getCardDataUseCase.getCardAboutData(),
                        artData = getCardDataUseCase.getCardArtData(),
                        miscData = getCardDataUseCase.getCardMiscData(),
                        showProgress = false
                    )
                }
            }
        }
        state = state.copy(
            tabs = listOf(DetailTab.ART, DetailTab.ABOUT, DetailTab.MISC),
            selected = DetailTab.ART,
        )
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
