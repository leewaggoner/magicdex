package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.components.HomeTopBar
import com.wreckingballsoftware.magicdex.ui.home.components.HomeMenuSection
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.home.models.HomeState
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navGraph: NavGraph,
    viewModel: HomeViewModel = getViewModel(),
) {
    viewModel.oneOffEvent.collectOneTimeEvents { oneOff ->
        when (oneOff) {
            HomeOneOffs.OnGoToMagicDex -> navGraph.navigateToMagicDexScreen()
        }
    }

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
    menuList: List<HomeMenuItem>,
) {
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
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(
                    start = MaterialTheme.dimensions.padding,
                    top = MaterialTheme.dimensions.padding,
                    end = MaterialTheme.dimensions.padding,
                )
        ) {
            HomeMenuSection(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.dimensions.paddingLarge),
                menuItems = menuList,
                onClick = { item ->
                    onEvent(HomeEvents.OnMenuItemClicked(item))
                },
            )
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
            HomeMenuItem(MenuItemType.MAGIC_DEX, R.string.magic_dex, LightGreen),
            HomeMenuItem(MenuItemType.SETS, R.string.sets, LightBlack),
            HomeMenuItem(MenuItemType.TYPES, R.string.types, LightRed),
            HomeMenuItem(MenuItemType.FORMATS, R.string.formats, LightBlue),
        ),
    )
}
