package com.wreckingballsoftware.magicdex.ui.home.models

import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType

sealed interface HomeEvents {
    data class OnSearchQueryChanged(val query: String) : HomeEvents
    data object OnSearchAction : HomeEvents
    data class OnMenuItemClicked(val item: MenuItemType) : HomeEvents
}
