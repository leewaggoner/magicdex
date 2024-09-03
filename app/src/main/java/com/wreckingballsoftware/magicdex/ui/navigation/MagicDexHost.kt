package com.wreckingballsoftware.magicdex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wreckingballsoftware.magicdex.ui.home.HomeScreen
import com.wreckingballsoftware.magicdex.ui.magicdex.MagicDexScreen

@Composable
fun MagicDexHost() {
    val navController = rememberNavController()
    val navGraph = remember(navController) { NavGraph(navController) }

    val startRoute = Routes.Home

    NavHost(navController = navController, startDestination = startRoute) {
        composable<Routes.Home> {
            HomeScreen(navGraph = navGraph)
        }

        composable<Routes.MagicDex> {
            MagicDexScreen(navGraph = navGraph)
        }
    }
}
