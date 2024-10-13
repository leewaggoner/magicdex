package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.components.HomeBottomBar
import com.wreckingballsoftware.magicdex.ui.components.HomeTopBar
import com.wreckingballsoftware.magicdex.ui.home.models.HomeScreenEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeScreenState
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.MagicDexHost
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
) {
    HomeScreenContent(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        menuList = viewModel.getMenuList(),
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeScreenState,
    onEvent: (HomeScreenEvents) -> Unit,
    menuList: List<TopLevelDestination>,
) {
    val navHostController = rememberNavController()
    val navGraph = remember(navHostController) { NavGraph(navHostController) }

    Scaffold(
        topBar = {
            HomeTopBar(
                title = stringResource(state.title),
                hasSearch = state.hasSearch,
                query = state.searchQuery,
                placeholder = stringResource(id = state.searchPlaceholder),
                onQueryChanged = { onEvent(HomeScreenEvents.OnSearchQueryChanged(it)) },
                onSearch = { onEvent(HomeScreenEvents.OnSearchAction) },
                onClear = {
                    onEvent(HomeScreenEvents.OnClearSearch)
                },
                onBack = if (state.hasBackButton) {
                    { onEvent(HomeScreenEvents.OnBack) }
                } else {
                    null
                },
            )
        },
        bottomBar = {
             HomeBottomBar(
                 destinations = menuList,
                 navGraph = navGraph,
                 onScreenChange = { route -> onEvent(HomeScreenEvents.OnScreenChange(route)) },
             )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            MagicDexHost(navHostController = navHostController, navGraph = navGraph)
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        state = HomeScreenState(),
        onEvent = {},
        menuList = listOf(
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
        ),
    )
}
