package com.wreckingballsoftware.magicdex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wreckingballsoftware.magicdex.ui.carddetail.CardDetailScreen
import com.wreckingballsoftware.magicdex.ui.cards.CardsScreen
import com.wreckingballsoftware.magicdex.ui.formats.FormatsScreen
import com.wreckingballsoftware.magicdex.ui.sets.SetsScreen
import com.wreckingballsoftware.magicdex.ui.types.TypesScreen

@Composable
fun MagicDexHost(
    navHostController: NavHostController,
    navGraph: NavGraph,
    searchQuery: String,
    ) {
    val startRoute = NavRoute.CardsRoot

    NavHost(navController = navHostController, startDestination = startRoute) {
        cardsGraph(
            navGraph,
            searchQuery,
        )
        setsGraph(navGraph)
        typesGraph(navGraph)
        formatsGraph(navGraph)
    }
}

fun NavGraphBuilder.cardsGraph(
    navGraph: NavGraph,
    searchQuery: String,
) {
    navigation<NavRoute.CardsRoot>(
        startDestination = NavRoute.Cards
    ) {
        composable<NavRoute.Cards> {
            CardsScreen(
                navGraph = navGraph,
                searchQuery = searchQuery
            )
        }
        composable<NavRoute.CardDetail> {
            CardDetailScreen(
                navGraph = navGraph,
            )
        }
    }
}

fun NavGraphBuilder.setsGraph(navGraph: NavGraph) {
    navigation<NavRoute.SetsRoot>(
        startDestination = NavRoute.Sets
    ) {
        composable<NavRoute.Sets> {
            SetsScreen(navGraph = navGraph)
        }
    }
}

fun NavGraphBuilder.typesGraph(navGraph: NavGraph) {
    navigation<NavRoute.TypesRoot>(
        startDestination = NavRoute.Types
    ) {
        composable<NavRoute.Types> {
            TypesScreen(navGraph = navGraph)
        }
    }
}

fun NavGraphBuilder.formatsGraph(navGraph: NavGraph) {
    navigation<NavRoute.FormatsRoot>(
        startDestination = NavRoute.Formats
    ) {
        composable<NavRoute.Formats> {
            FormatsScreen(navGraph = navGraph)
        }
    }
}
