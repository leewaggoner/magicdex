package com.wreckingballsoftware.magicdex.ui.magicscaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.components.ScaffoldBottomBar
import com.wreckingballsoftware.magicdex.ui.components.ScaffoldTopBar
import com.wreckingballsoftware.magicdex.ui.magicscaffold.models.MagicScaffoldEvents
import com.wreckingballsoftware.magicdex.ui.magicscaffold.models.MagicScaffoldOneOffs
import com.wreckingballsoftware.magicdex.ui.magicscaffold.models.MagicScaffoldState
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.MagicDexHost
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute
import org.koin.androidx.compose.getViewModel

@Composable
fun MagicScaffold(
    viewModel: MagicScaffoldViewModel = getViewModel(),
) {
    //for MagicDexHost
    val navHostController = rememberNavController()
    val navGraph = remember(navHostController) { NavGraph(navHostController) }

    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
            is MagicScaffoldOneOffs.OnBack -> navGraph.navigateBack()
        }
    }

    val navBackStackEntry = navGraph.navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = NavRoute.fromDestination(currentDestination)
    LaunchedEffect(key1 = currentRoute) {
        //set ScaffoldTopBar state
        viewModel.onEvent(MagicScaffoldEvents.OnScreenChange(currentRoute))
    }

    MagicScaffoldContent(
        magicDexHost = {
            MagicDexHost(
                navHostController = navHostController,
                navGraph = navGraph,
                searchQuery = viewModel.state.searchQuery,
            )
        },
        navGraph = navGraph,
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        menuList = viewModel.getMenuList(),
    )
}

@Composable
private fun MagicScaffoldContent(
    magicDexHost: @Composable () -> Unit,
    navGraph: NavGraph,
    state: MagicScaffoldState,
    onEvent: (MagicScaffoldEvents) -> Unit,
    menuList: List<TopLevelDestination>,
) {
    Scaffold(
        topBar = {
            ScaffoldTopBar(
                title = stringResource(state.title),
                hasSearch = state.hasSearch,
                query = state.searchQuery,
                placeholder = stringResource(id = state.searchPlaceholder),
                onQueryChanged = { onEvent(MagicScaffoldEvents.OnSearchQueryChanged(it)) },
                onSearch = { onEvent(MagicScaffoldEvents.OnSearchAction) },
                onClear = {
                    onEvent(MagicScaffoldEvents.OnClearSearch)
                },
                onBack = if (state.hasBackButton) {
                    { onEvent(MagicScaffoldEvents.OnBack) }
                } else {
                    null
                },
            )
        },
        bottomBar = {
             ScaffoldBottomBar(
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
            magicDexHost()
        }
    }
}

@Preview(name = "Magic Scaffold Content", showSystemUi = true)
@Composable
private fun MagicScaffoldPreview() {
    MagicScaffoldContent(
        magicDexHost = { },
        navGraph = NavGraph(rememberNavController()),
        state = MagicScaffoldState(),
        onEvent = { },
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
