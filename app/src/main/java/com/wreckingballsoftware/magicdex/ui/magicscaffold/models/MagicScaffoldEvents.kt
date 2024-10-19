package com.wreckingballsoftware.magicdex.ui.magicscaffold.models

import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute

sealed interface MagicScaffoldEvents {
    data class OnSearchQueryChanged(val query: String) : MagicScaffoldEvents
    data object OnSearchAction : MagicScaffoldEvents
    data object OnClearSearch : MagicScaffoldEvents
    data class OnScreenChange(val route: NavRoute) : MagicScaffoldEvents
    data object OnBack : MagicScaffoldEvents
}
