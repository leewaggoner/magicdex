package com.wreckingballsoftware.magicdex.ui.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    data object InvalidScreen : NavRoute
    @Serializable
    data object CardsRoot : NavRoute
    @Serializable
    data object Cards : NavRoute
    @Serializable
    data class CardDetail(val cardId: String) : NavRoute
    @Serializable
    data object SetsRoot : NavRoute
    @Serializable
    data object Sets : NavRoute
    @Serializable
    data object TypesRoot : NavRoute
    @Serializable
    data object Types : NavRoute
    @Serializable
    data object FormatsRoot : NavRoute
    @Serializable
    data object Formats : NavRoute

    companion object {
        fun fromDestination(currentDestination: NavDestination?): NavRoute {
            return when {
                currentDestination?.hasRoute(CardDetail::class) == true -> CardDetail(cardId = "id")
                else -> InvalidScreen
            }
        }
    }
}
