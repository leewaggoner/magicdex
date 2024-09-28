package com.wreckingballsoftware.magicdex.ui.magicdex.models

sealed interface MagicDexEvent {
    data class ApiError(val message: String) : MagicDexEvent
    data object DismissDialog : MagicDexEvent
}