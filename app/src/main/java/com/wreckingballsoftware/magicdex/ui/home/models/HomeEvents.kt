package com.wreckingballsoftware.magicdex.ui.home.models

sealed interface HomeEvents {
    data class OnSearchQueryChanged(val query: String) : HomeEvents
    data object OnSearchAction : HomeEvents
    data object OnClearSearch : HomeEvents
    data object OnBack : HomeEvents
}
