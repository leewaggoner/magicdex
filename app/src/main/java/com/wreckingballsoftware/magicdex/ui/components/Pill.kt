package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.ui.theme.PillBorder
import com.wreckingballsoftware.magicdex.ui.theme.Transparent
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun Pill(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .border(
                    width = MaterialTheme.dimensions.pillBorder,
                    color = PillBorder,
                    shape = MaterialTheme.shapes.small,
                )
                .clip(MaterialTheme.shapes.small)
                .background(color = color ?: Transparent)
        )
    ) {
        Text(
            modifier = modifier
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
}

@Preview(name = "Pill")
@Composable
private fun PillPreview() {
    Pill(text = "Pill text for looking at.")
}