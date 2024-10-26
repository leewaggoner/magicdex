package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.ui.theme.Blue

@Composable
fun NoTouchCircularProgress() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { },
            )
    ) {
        CircularProgressIndicator(color = Blue)
    }
}

@Preview
@Composable
fun NoTouchCircularProgressPreview() {
    NoTouchCircularProgress()
}