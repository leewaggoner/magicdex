package com.wreckingballsoftware.magicdex.ui.navigation

import androidx.navigation.NavController

class NavGraph(navController: NavController) {
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(
            Routes.Home
        ) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateToMagicDexScreen: () -> Unit = {
        navController.navigate(
            Routes.MagicDex
        ) {
        }
    }
}
