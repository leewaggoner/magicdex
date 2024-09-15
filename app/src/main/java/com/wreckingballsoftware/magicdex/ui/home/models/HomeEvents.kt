package com.wreckingballsoftware.magicdex.ui.home.models

import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType

sealed interface HomeEvents {
    data object OnGetNews : HomeEvents
    data class OnSearchQueryChanged(val query: String) : HomeEvents
    data object OnSearchAction : HomeEvents
    data class OnMenuItemClicked(val item: MenuItemType) : HomeEvents
    data object OnViewAllNews : HomeEvents
    data class OnViewNewsItem(val link: String) : HomeEvents
}
