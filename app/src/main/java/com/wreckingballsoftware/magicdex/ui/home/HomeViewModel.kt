package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

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
//            is HomeEvents.OnMagicDexClick -> handleMagicDexClick()
//            HomeEvents.OnToastClick -> showToast()
//            HomeEvents.OnAlertDialogClick -> showDialog()
            HomeEvents.OnSearchAction -> onSearchAction()
            is HomeEvents.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
        }
    }

    private fun onSearchAction() {
    }

    private fun onSearchQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
    }

//    private fun handleMagicDexClick() {
//        viewModelScope.launch {
//            _oneOffEvent.emit(HomeOneOffs.OnGoToMagicDex)
//        }
//    }
//
//    private fun showToast() {
//        count++
//        viewModelScope.launch {
//            _oneOffEvent.emit(HomeOneOffs.OnShowToast("this is toast $count"))
//        }
//    }
//
//    private fun showDialog() {
//        count++
//        state = state.copy(alertMessage = "this is alert dialog $count")
//    }
//
//    fun dismissDialog() {
//        state = state.copy(alertMessage = null)
//    }
}
