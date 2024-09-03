package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    handle: SavedStateHandle
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(HomeState())
    }

    private val _oneOffEvent = MutableSharedFlow<HomeOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    private var count = 0

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnMagicDexClick -> handleMagicDexClick()
            HomeEvents.OnToastClick -> showToast()
            HomeEvents.OnAlertDialogClick -> showDialog()
        }
    }

    private fun handleMagicDexClick() {
        viewModelScope.launch {
            _oneOffEvent.emit(HomeOneOffs.OnGoToMagicDex)
        }
    }

    private fun showToast() {
        count++
        viewModelScope.launch {
            _oneOffEvent.emit(HomeOneOffs.OnShowToast("this is toast $count"))
        }
    }

    private fun showDialog() {
        count++
        state = state.copy(alertMessage = "this is alert dialog $count")
    }

    fun dismissDialog() {
        state = state.copy(alertMessage = null)
    }
}