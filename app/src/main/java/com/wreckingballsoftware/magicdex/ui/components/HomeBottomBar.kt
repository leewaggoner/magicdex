package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute

@Composable
fun HomeBottomBar(
    destinations: List<TopLevelDestination>,
    navGraph: NavGraph,
) {
    val navBackStackEntry = navGraph.navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id =  destination.icon),
                        contentDescription = stringResource(id = destination.label),
                    )
                },
                label = {
                    Text(text = stringResource(id = destination.label))
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(destination.route::class)
                } == true,
                onClick = {
                    if (currentDestination?.hierarchy?.any {
                            it.hasRoute(destination.route::class)
                        } == false) {
                        navGraph.navigateToDestination(destination.route)
                    }
                }
            )
        }
    }
}

@Preview(name = "Home Bottom Bar")
@Composable
fun HomeBottomBarPreview() {
    val navHostController = rememberNavController()
    HomeBottomBar(
        destinations = listOf(
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
        navGraph = NavGraph(navHostController)
    )
}