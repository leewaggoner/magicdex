package com.wreckingballsoftware.magicdex.ui.magicdex

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.repos.CardRepo
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MagicDexViewModel(
    private val cardRepo: CardRepo,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MagicDexState())
    }

    private val _cards: MutableStateFlow<List<Card>> = MutableStateFlow(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    fun loadCards() {
        viewModelScope.launch {
            cardRepo.getCards(1).fold(
                onSuccess = { cards ->
                    _cards.update { cards }
                }, onFailure = { error ->
                    // something went wrong
                    Log.e("MagicDexViewModel", "Error: ${error.message}", error)
                }
            )
        }
    }
}
