package com.wreckingballsoftware.magicdex.ui.carddetail.models

sealed interface CardDetailEvents {
    data object OnBack : CardDetailEvents
}