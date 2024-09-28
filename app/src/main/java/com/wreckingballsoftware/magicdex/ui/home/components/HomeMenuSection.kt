package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import com.wreckingballsoftware.magicdex.ui.theme.dimensions

@Composable
fun HomeMenuSection(
    modifier: Modifier = Modifier,
    menuItems: List<HomeMenuItem>,
    onClick: (MenuItemType) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.then(
            Modifier
                .height(MaterialTheme.dimensions.menuSectionHeight)
                .fillMaxWidth()
        ),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spaceSmall),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spaceSmall),
    ) {
        items(menuItems.size) { index ->
            HomeMenu(menuItems[index], onClick)
        }
    }
}

@Preview(name = "HomeMenuSection")
@Composable
private fun HomeMenuSectionPreview() {
    HomeMenuSection(
        menuItems = listOf(
            HomeMenuItem(MenuItemType.MAGIC_DEX, R.string.magic_dex, LightGreen),
            HomeMenuItem(MenuItemType.SETS, R.string.sets, LightBlack),
            HomeMenuItem(MenuItemType.TYPES, R.string.types, LightRed),
            HomeMenuItem(MenuItemType.FORMATS, R.string.formats, LightBlue),
        ),
        onClick = { },
    )
}