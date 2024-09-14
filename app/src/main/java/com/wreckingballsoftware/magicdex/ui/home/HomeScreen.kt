package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.HomeMenuSection
import com.wreckingballsoftware.magicdex.ui.home.components.MagicTopAppBar
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
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
    viewModel: HomeViewModel = getViewModel()
) {

    HomeScreenContent(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        menuList = viewModel.getMenuList()
    )
}

@Composable
fun HomeScreenContent(
    state: HomeState,
    onEvent: (HomeEvents) -> Unit,
    menuList: List<HomeMenuItem>,
) {
    Scaffold(
        topBar = {
            MagicTopAppBar(
                searchQuery = state.searchQuery,
                searchQueryChanged = { onEvent(HomeEvents.OnSearchQueryChanged(it)) },
                searchAction = { onEvent(HomeEvents.OnSearchAction) }
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(
                    vertical = MaterialTheme.dimensions.paddingLarge,
                    horizontal = MaterialTheme.dimensions.padding
                )
                .verticalScroll(rememberScrollState())
        ) {
            HomeMenuSection(
                menuItems = menuList,
                onClick = { item ->
                    onEvent(HomeEvents.OnMenuItemClicked(item))
                }
            )
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        state = HomeState(),
        onEvent = {},
        menuList = listOf(
            HomeMenuItem(MenuItemType.MAGIC_DEX, R.string.magic_dex, LightGreen),
            HomeMenuItem(MenuItemType.SETS, R.string.sets, LightBlack),
            HomeMenuItem(MenuItemType.TYPES, R.string.types, LightRed),
            HomeMenuItem(MenuItemType.FORMATS, R.string.formats, LightBlue),
        )
    )
}
