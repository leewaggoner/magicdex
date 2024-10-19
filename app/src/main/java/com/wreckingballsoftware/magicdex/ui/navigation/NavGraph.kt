package com.wreckingballsoftware.magicdex.ui.navigation

import androidx.navigation.NavController

class NavGraph(val navController: NavController) {
    val navigateToDestination: (destination: NavRoute) -> Unit = { route ->
        when (route) {
            NavRoute.Cards -> navigateToCardsScreen()
            NavRoute.Sets -> navigateToSetsScreen()
            NavRoute.Types -> navigateToTypesScreen()
            NavRoute.Formats -> navigateToFormatsScreen()
            else -> { }
        }
    }
    val navigateToCardsScreen: () -> Unit = {
        navController.navigate(
            NavRoute.Cards
        ) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateToCardDetailScreen: (cardId: String) -> Unit = { cardId ->
        navController.navigate(
            NavRoute.CardDetail(cardId)
        )
    }
    val navigateToSetsScreen: () -> Unit = {
        navController.navigate(
            NavRoute.Sets
        ) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateToTypesScreen: () -> Unit = {
        navController.navigate(
            NavRoute.Types
        ) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateToFormatsScreen: () -> Unit = {
        navController.navigate(
            NavRoute.Formats
        ) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }
}
