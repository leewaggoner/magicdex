package com.wreckingballsoftware.magicdex.ui.home.models

import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute

sealed interface HomeScreenEvents {
    data class OnSearchQueryChanged(val query: String) : HomeScreenEvents
    data object OnSearchAction : HomeScreenEvents
    data object OnClearSearch : HomeScreenEvents
    data class OnScreenChange(val route: NavRoute) : HomeScreenEvents
    data object OnBack : HomeScreenEvents
}
