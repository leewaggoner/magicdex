package com.wreckingballsoftware.magicdex.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    object Home
    @Serializable
    object MagicDex
}
