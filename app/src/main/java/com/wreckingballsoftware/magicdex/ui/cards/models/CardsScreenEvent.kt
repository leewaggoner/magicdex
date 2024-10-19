package com.wreckingballsoftware.magicdex.ui.cards.models

sealed interface CardsScreenEvent {
    data class Search(val query: String) : CardsScreenEvent
    data class ApiError(val message: String) : CardsScreenEvent
    data object DismissDialog : CardsScreenEvent
    data class OnCardSelected(val cardId: String) : CardsScreenEvent
}