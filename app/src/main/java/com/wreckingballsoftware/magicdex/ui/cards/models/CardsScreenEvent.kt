package com.wreckingballsoftware.magicdex.ui.cards.models

sealed interface CardsScreenEvent {
    data class ApiError(val message: String) : CardsScreenEvent
    data object DismissDialog : CardsScreenEvent
}