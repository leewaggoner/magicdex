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
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexEvent
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexOneOffs
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class MagicDexViewModel(
    private val cardRepo: CardRepo,
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MagicDexState())
    }

//    From: https://proandroiddev.com/loading-initial-data-in-launchedeffect-vs-viewmodel-f1747c20ce62
    private val cardPageIndex: MutableStateFlow<Int> = MutableStateFlow(1)
    @OptIn(ExperimentalCoroutinesApi::class)
    val cardList: StateFlow<Result<List<Card>>> = cardPageIndex.flatMapLatest { page ->
        cardRepo.getCardsFlow(page = page)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Result.success(emptyList())
    )
    private val _oneOffEvent = MutableSharedFlow<MagicDexOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    fun onEvent(event: MagicDexEvent) {
        when (event) {
            is MagicDexEvent.ApiError -> {
                Log.e("MagicDexViewModel", "Error: ${event.ex.message}", event.ex)
                onApiError(event.ex)
            }
            MagicDexEvent.DismissDialog -> state = state.copy(alertMessage = null)
        }
    }

    private fun onApiError(ex: Throwable) {
        state = state.copy(alertMessage = ex.localizedMessage ?: "Unknown error")
    }
}
