package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import com.wreckingballsoftware.magicdex.ui.theme.White

@Composable
fun CardDetailTab(
    modifier: Modifier = Modifier,
    tabs: List<Int>,
    selected: Int,
    onClick: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = 0,
        containerColor = LightRed,
        contentColor = White,
        indicator = { tabPositions ->
            if (selected < tabPositions.size) {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selected]),
                    color = LightBlue
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(text = stringResource(id = tab))
               },
                selected = index == selected,
                onClick = { onClick(index) }
            )
        }
    }
}

@Preview(name = "CardDetailTab")
@Composable
fun CardDetailTabPreview() {
    CardDetailTab(
        tabs = listOf(R.string.about, R.string.card_text, R.string.misc),
        selected = 0,
        onClick = { }
    )
}