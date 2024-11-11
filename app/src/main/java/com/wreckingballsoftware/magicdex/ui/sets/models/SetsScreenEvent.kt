package com.wreckingballsoftware.magicdex.ui.sets.models

sealed interface SetsScreenEvent {
    data class ApiError(val message: String) : SetsScreenEvent
    data class Search(val query: String) : SetsScreenEvent
}
