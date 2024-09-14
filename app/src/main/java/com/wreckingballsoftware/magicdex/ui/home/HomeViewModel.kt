package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HomeViewModel(
    handle: SavedStateHandle
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(HomeState())
    }

    private val _oneOffEvent = MutableSharedFlow<HomeOneOffs>()
    val oneOffEvent = _oneOffEvent.asSharedFlow()

    private var count = 0

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnSearchAction -> onSearchAction()
            is HomeEvents.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
            is HomeEvents.OnMenuItemClicked -> onMenuItemClicked(event.item)
        }
    }

    fun getMenuList(): List<HomeMenuItem> =
        MenuItemType.entries.map { menuItemType ->
            when (menuItemType) {
                MenuItemType.MAGIC_DEX -> HomeMenuItem(menuItemType, R.string.magic_dex, LightGreen)
                MenuItemType.SETS -> HomeMenuItem(menuItemType, R.string.sets, LightBlack)
                MenuItemType.TYPES -> HomeMenuItem(menuItemType, R.string.types, LightRed)
                MenuItemType.FORMATS -> HomeMenuItem(menuItemType, R.string.formats, LightBlue)
            }
        }

    private fun onSearchAction() {
    }

    private fun onSearchQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
    }

    private fun onMenuItemClicked(item: MenuItemType) {
        val menuItem = item
    }
}
