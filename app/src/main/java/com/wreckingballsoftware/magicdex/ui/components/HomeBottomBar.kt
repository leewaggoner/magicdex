package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import com.wreckingballsoftware.magicdex.ui.models.TopLevelDestination
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph

@Composable
fun HomeBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: TopLevelDestination,
    navGraph: NavGraph,
) {
    NavigationBar {
        destinations.forEach { destination ->
//            NavigationBarItem(
//                icon = destination.icon,
//                label = destination.label,
//                selected = currentDestination == destination,
//                onClick = {
//                    navGraph.navigateToDestination(destination.route)
//                }
//            )
        }
    }
}