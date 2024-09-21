package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexState

class MagicDexViewModel(
    handle: SavedStateHandle
): ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(MagicDexState())
    }
}