package com.wreckingballsoftware.magicdex.ui.magicdex.models

sealed interface MagicDexEvent {
    data class ApiError(val ex: Throwable) : MagicDexEvent
    data object DismissDialog : MagicDexEvent
}