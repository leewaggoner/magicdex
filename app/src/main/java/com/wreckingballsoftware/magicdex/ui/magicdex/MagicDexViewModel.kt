package com.wreckingballsoftware.magicdex.ui.magicdex

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.network.ApiResult
import com.wreckingballsoftware.magicdex.data.repos.CardRepo
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexEvent
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexOneOffs
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MagicDexViewModel(
    private val cardRepo: CardRepo,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MagicDexState())
    }
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards = _cards.asStateFlow()
    private val _oneOffEvent = MutableSharedFlow<MagicDexOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            getCards(1)
        }
    }

    fun onEvent(event: MagicDexEvent) {
        when (event) {
            is MagicDexEvent.ApiError -> {
                onApiError(event.message)
            }
            MagicDexEvent.DismissDialog -> state = state.copy(alertMessage = null)
        }
    }

    private suspend fun getCards(page: Int) {
        cardRepo.getCards(page).collectLatest { result ->
            when(result) {
                is ApiResult.Success -> {
                    state = state.copy(isLoading = false)
                    _cards.value = result.data
                }
                is ApiResult.Error -> {
                    Log.e(
                        "MagicDexViewModel",
                        "Error: ${result.exception.message}",
                        result.exception
                    )
                    onEvent(MagicDexEvent.ApiError(result.exception.message ?: "Unknown error"))
                }
                ApiResult.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }
    }

    private fun onApiError(message: String) {
        state = state.copy(alertMessage = message)
    }
}
