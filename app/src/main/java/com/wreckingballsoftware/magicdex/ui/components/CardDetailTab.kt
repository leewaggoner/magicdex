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
import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import com.wreckingballsoftware.magicdex.ui.theme.White

@Composable
fun CardDetailTab(
    modifier: Modifier = Modifier,
    tabs: List<DetailTab>,
    selected: DetailTab,
    onClick: (DetailTab) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = 0,
        containerColor = LightRed,
        contentColor = White,
        indicator = { tabPositions ->
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selected.ordinal]),
                color = LightBlue
            )
        }
    ) {
        tabs.forEach { tab ->
            Tab(
                text = {
                    Text(text = stringResource(id = tab.titleId))
               },
                selected = tab == selected,
                onClick = { onClick(tab) }
            )
        }
    }
}

@Preview(name = "CardDetailTab")
@Composable
fun CardDetailTabPreview() {
    CardDetailTab(
        tabs = listOf(DetailTab.ABOUT, DetailTab.ART, DetailTab.MISC),
        selected = DetailTab.ABOUT,
        onClick = { }
    )
}