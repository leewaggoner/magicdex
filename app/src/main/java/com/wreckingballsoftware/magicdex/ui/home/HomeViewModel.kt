package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.models.HomeScreenEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeScreenState
import com.wreckingballsoftware.magicdex.ui.models.TopBarState
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute

class HomeViewModel(
    handle: SavedStateHandle,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state by handle.saveable {
        mutableStateOf(HomeScreenState())
    }

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            HomeScreenEvents.OnSearchAction -> onSearchAction()
            is HomeScreenEvents.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
            HomeScreenEvents.OnClearSearch -> onClearSearch()
            is HomeScreenEvents.OnScreenChange -> onScreenChange(event.route)
            HomeScreenEvents.OnBack -> onBack()
        }
    }

    fun getMenuList(): List<TopLevelDestination> =
        listOf(
            TopLevelDestination(
                route = NavRoute.Cards,
                label = R.string.cards,
                icon = R.drawable.ico_card
            ),
            TopLevelDestination(
                route = NavRoute.Sets,
                label = R.string.sets,
                icon = R.drawable.ico_set
            ),
            TopLevelDestination(
                route = NavRoute.Types,
                label = R.string.types,
                icon = R.drawable.ico_type
            ),
            TopLevelDestination(
                route = NavRoute.Formats,
                label = R.string.formats,
                icon = R.drawable.ico_format
            ),
        )

    private fun onSearchAction() {
    }

    private fun onSearchQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
    }

    private fun onClearSearch() {
        state = state.copy(searchQuery = "")
    }

    private fun onScreenChange(route: NavRoute) {
        state = when (route) {
            NavRoute.Cards -> {
                state.copy(
                    title = TopBarState.CARD.title,
                    hasSearch = TopBarState.CARD.hasSearch,
                    searchPlaceholder = TopBarState.CARD.searchPlaceholder,
                    hasBackButton = TopBarState.CARD.hasBackButton,
                )
            }
            NavRoute.Sets -> {
                state.copy(
                    title = TopBarState.SET.title,
                    hasSearch = TopBarState.SET.hasSearch,
                    searchPlaceholder = TopBarState.SET.searchPlaceholder,
                    hasBackButton = TopBarState.SET.hasBackButton,
                )
            }
            NavRoute.Types -> {
                state.copy(
                    title = TopBarState.TYPE.title,
                    hasSearch = TopBarState.TYPE.hasSearch,
                    searchPlaceholder = TopBarState.TYPE.searchPlaceholder,
                    hasBackButton = TopBarState.TYPE.hasBackButton,
                )
            }
            NavRoute.Formats -> {
                state.copy(
                    title = TopBarState.FORMAT.title,
                    hasSearch = TopBarState.FORMAT.hasSearch,
                    searchPlaceholder = TopBarState.FORMAT.searchPlaceholder,
                    hasBackButton = TopBarState.FORMAT.hasBackButton,
                )
            }
            else -> {
                state
            }
        }
    }

    private fun onBack() {
    }
}
