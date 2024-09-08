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
    val spaceSmall: Dp = 16.dp,
    val spaceMedium: Dp = 32.dp,
    val spaceLarge: Dp = 64.dp,
    val appBarHeight: Dp = 250.dp,
    val appBarImageSize: Dp = 240.dp,
    val appBarImageOffset: Dp = 90.dp,
    val appBarCorner: Dp = 16.dp,
    val searchCorner: Dp = 32.dp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current
