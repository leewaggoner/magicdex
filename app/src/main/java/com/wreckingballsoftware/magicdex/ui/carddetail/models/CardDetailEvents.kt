package com.wreckingballsoftware.magicdex.ui.carddetail.models

sealed interface CardDetailEvents {
    data class OnTabSelected(val index: Int) : CardDetailEvents
}