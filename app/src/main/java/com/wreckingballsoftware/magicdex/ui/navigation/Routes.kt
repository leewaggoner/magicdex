package com.wreckingballsoftware.magicdex.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    object Home
    @Serializable
    data class MagicDex(val id: String)
}
