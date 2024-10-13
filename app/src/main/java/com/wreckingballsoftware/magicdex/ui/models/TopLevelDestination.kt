package com.wreckingballsoftware.magicdex.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data class TopLevelDestination(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val route: NavRoute,
)
