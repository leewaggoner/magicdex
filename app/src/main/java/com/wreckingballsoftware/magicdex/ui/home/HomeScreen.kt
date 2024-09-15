package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.HomeMenuSection
import com.wreckingballsoftware.magicdex.ui.home.components.MagicTopAppBar
import com.wreckingballsoftware.magicdex.ui.home.components.NewsSection
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.home.components.models.News
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
    viewModel: HomeViewModel = getViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvents.OnGetNews)
    }

    HomeScreenContent(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        menuList = viewModel.getMenuList(),
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
                searchAction = { onEvent(HomeEvents.OnSearchAction) },
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
            NewsSection(
                newsList = state.newsList,
                onViewAllClick = { onEvent(HomeEvents.OnViewAllNews) },
                onViewItemClick = { link -> onEvent(HomeEvents.OnViewNewsItem(link)) },
            )
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        state = HomeState(
            newsList = listOf(
                News(title = "Title 1", date = "10/15/2024"),
                News(title = "Title 2", date = "10/14/2024"),
                News(title = "Title 3", date = "10/13/2024"),
                News(title = "Title 4", date = "10/12/2024"),
                News(title = "Title 5", date = "10/11/2024"),
                News(title = "Title 6", date = "10/10/2024"),
                News(title = "Title 7", date = "10/09/2024"),
                News(title = "Title 8", date = "10/08/2024"),
                News(title = "Title 9", date = "10/07/2024"),
                News(title = "Title 10", date = "10/06/2024"),
            )
        ),
        onEvent = {},
        menuList = listOf(
            HomeMenuItem(MenuItemType.MAGIC_DEX, R.string.magic_dex, LightGreen),
            HomeMenuItem(MenuItemType.SETS, R.string.sets, LightBlack),
            HomeMenuItem(MenuItemType.TYPES, R.string.types, LightRed),
            HomeMenuItem(MenuItemType.FORMATS, R.string.formats, LightBlue),
        ),
    )
}
