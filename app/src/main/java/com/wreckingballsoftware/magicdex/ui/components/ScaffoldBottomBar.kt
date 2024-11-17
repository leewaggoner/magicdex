package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute
import com.wreckingballsoftware.magicdex.ui.theme.Black
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions

@Composable
fun ScaffoldBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    navGraph: NavGraph,
) {
    val navBackStackEntry = navGraph.navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier,
        containerColor = Black,
    ) {
        destinations.forEach { destination ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = LightGreen,
                ),
                icon = {
                    Icon(
                        modifier = Modifier.size(size = MaterialTheme.dimensions.bottomBarIconSize),
                        painter = painterResource(id =  destination.icon),
                        contentDescription = stringResource(id = destination.label),
                        tint = White,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.label),
                        color = White,
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(route = destination.route::class)
                } == true,
                onClick = {
                    if (currentDestination?.hierarchy?.any {
                            it.hasRoute(route = destination.route::class)
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
    ScaffoldBottomBar(
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
        ),
        navGraph = NavGraph(navHostController),
    )
}