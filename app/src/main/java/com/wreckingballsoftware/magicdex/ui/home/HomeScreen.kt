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
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
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
    state: HomeState,
    onEvent: (HomeEvents) -> Unit,
    menuList: List<TopLevelDestination>,
) {
    val navHostController = rememberNavController()
    val navGraph = remember(navHostController) { NavGraph(navHostController) }

    Scaffold(
        topBar = {
            HomeTopBar(
                title = stringResource(id = R.string.app_name),
                query = state.searchQuery,
                placeholder = state.searchPlaceholder,
                onQueryChanged = { onEvent(HomeEvents.OnSearchQueryChanged(it)) },
                onSearch = { onEvent(HomeEvents.OnSearchAction) },
                onClear = {
                    onEvent(HomeEvents.OnClearSearch)
                },
                onBack = if (state.hasBackButton) {
                    { onEvent(HomeEvents.OnBack) }
                } else {
                    null
                },
            )
        },
        bottomBar = {
             HomeBottomBar(
                 destinations = menuList,
                 navGraph = navGraph,
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
        state = HomeState(),
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
