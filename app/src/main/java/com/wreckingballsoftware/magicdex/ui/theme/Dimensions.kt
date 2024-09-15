package com.wreckingballsoftware.magicdex.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val paddingVerySmall: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val padding: Dp = 16.dp,
    val paddingLarge: Dp = 32.dp,
    val spaceSmall: Dp = 8.dp,
    val space: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceVeryLarge: Dp = 64.dp,
    val appBarHeight: Dp = 200.dp,
    val appBarImageSize: Dp = 200.dp,
    val appBarImageOffset: Dp = 70.dp,
    val appBarCorner: Dp = 16.dp,
    val searchCorner: Dp = 32.dp,
    val homeMenuItemHeight: Dp = 60.dp,
    val homeMenuImageSize: Dp = 96.dp,
    val homeMenuImageOffset: Dp = 32.dp,
    val menuSectionHeight: Dp = 128.dp,
    val newsCardHeight: Dp = 100.dp,
    val newsImageCorner: Dp = 8.dp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current
