package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.ui.theme.PillBorder
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun Pill(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier
            .border(
                width = 1.dp,
                color = PillBorder,
                shape = MaterialTheme.shapes.small,
            )
            .padding(
                horizontal = MaterialTheme.dimensions.paddingSmall,
                vertical = MaterialTheme.dimensions.paddingTiny,
            ),
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.magicTypography.labelOnDark,
    )
}

@Preview(name = "Pill")
@Composable
private fun PillPreview() {
    Pill(text = "Pill text for looking at.")
}