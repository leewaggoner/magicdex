package com.wreckingballsoftware.magicdex.ui.home.components.models

import androidx.compose.ui.graphics.Color

enum class MenuItemType {
    MAGIC_DEX,
    SETS,
    TYPES,
    FORMATS,
}

data class HomeMenuItem(
    val type: MenuItemType,
    val nameId: Int,
    val color: Color,
)