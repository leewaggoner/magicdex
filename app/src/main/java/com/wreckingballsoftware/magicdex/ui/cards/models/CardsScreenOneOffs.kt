package com.wreckingballsoftware.magicdex.ui.cards.models

sealed interface CardsScreenOneOffs {
    data class NavigateToCardDetail(val cardId: String) : CardsScreenOneOffs
    data class ShowError(val message: String) : CardsScreenOneOffs
}