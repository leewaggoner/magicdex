package com.wreckingballsoftware.magicdex.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
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
}
