package com.wreckingballsoftware.magicdex.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class Dimensions(
    val paddingVeryTiny: Dp = 2.dp,
    val paddingTiny: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val padding: Dp = 16.dp,
    val paddingLarge: Dp = 32.dp,
    val spaceSmall: Dp = 8.dp,
    val space: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceVeryLarge: Dp = 64.dp,
    val topBarHeight: Dp = 148.dp,
    val topBarHeightNoSearch: Dp = 88.dp,
    val topBarImageSize: Dp = 200.dp,
    val topBarImageOffsetX: Dp = 96.dp,
    val topBarImageOffsetY: Dp = 28.dp,
    val bottomBarIconSize: Dp = 24.dp,
    val appBarCorner: Dp = 32.dp,
    val searchCorner: Dp = 32.dp,
    val homeMenuItemHeight: Dp = 60.dp,
    val homeMenuImageSize: Dp = 96.dp,
    val homeMenuImageOffset: Dp = 32.dp,
    val magicCardHeight: Dp = 200.dp,
    val magicCardCorner: Dp = 4.dp,
    val magicCardImageHeight: Dp = 120.dp,
    val magicCardManaSize: Dp = 16.dp,
    val pillBorder: Dp = 1.dp,
    val sheetPeekHeight: Dp = 148.dp,

    val magicCardManaTextSize: TextUnit = 16.sp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current
