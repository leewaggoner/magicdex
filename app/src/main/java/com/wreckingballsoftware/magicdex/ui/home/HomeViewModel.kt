package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.home.components.models.News
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import kotlinx.coroutines.flow.MutableSharedFlow

class HomeViewModel(
    handle: SavedStateHandle,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(HomeState())
    }

    private val _oneOffEvent = MutableSharedFlow<HomeOneOffs>()
    val oneOffEvent = _oneOffEvent

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnGetNews -> onGetNews()
            HomeEvents.OnSearchAction -> onSearchAction()
            is HomeEvents.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
            is HomeEvents.OnMenuItemClicked -> onMenuItemClicked(event.item)
            HomeEvents.OnViewAllNews -> onViewAllNews()
            is HomeEvents.OnViewNewsItem -> onViewNewsItem(event.link)
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

    private fun onGetNews() {
        state = state.copy(
            newsList = listOf(
                News(title = "Title 1", date = "10/15/2024", link = "https://www.google1.com"),
                News(title = "Title 2", date = "10/14/2024", link = "https://www.google2.com"),
                News(title = "Title 3", date = "10/13/2024", link = "https://www.google3.com"),
                News(title = "Title 4", date = "10/12/2024", link = "https://www.google4.com"),
                News(title = "Title 5", date = "10/11/2024", link = "https://www.google5.com"),
                News(title = "Title 6", date = "10/10/2024", link = "https://www.google6.com"),
                News(title = "Title 7", date = "10/09/2024", link = "https://www.google7.com"),
                News(title = "Title 8", date = "10/08/2024", link = "https://www.google8.com"),
                News(title = "Title 9", date = "10/07/2024", link = "https://www.google9.com"),
                News(title = "Title 10", date = "10/06/2024", link = "https://www.google10.com"),
            )
        )
    }

    private fun onSearchAction() {
    }

    private fun onSearchQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
    }

    private fun onMenuItemClicked(item: MenuItemType) {
        val menuItem = item
    }

    private fun onViewAllNews() {
    }

    private fun onViewNewsItem(link: String) {
        val urlLink = link
    }
}
