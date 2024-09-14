package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.HomeMenuItem
import com.wreckingballsoftware.magicdex.ui.home.components.models.MenuItemType
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun HomeMenu(menuItem: HomeMenuItem, onClick: (MenuItemType) -> Unit) {
    Card(
        modifier = Modifier
            .height(MaterialTheme.dimensions.homeMenuItemHeight)
            .fillMaxWidth()
            .clickable { onClick(menuItem.type) },
        colors = CardDefaults.cardColors(
            containerColor = menuItem.color,
        ),
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.dimensions.padding),
            text = stringResource(menuItem.nameId),
            style = MaterialTheme.magicTypography.homeMenu
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.CenterStart, unbounded = true)
                    .align(Alignment.CenterEnd)
                    .size(MaterialTheme.dimensions.homeMenuImageSize)
                    .offset(
                        x = MaterialTheme.dimensions.homeMenuImageOffset,
                        y = -MaterialTheme.dimensions.homeMenuImageOffset
                    )
                    .alpha(0.25f),
                colorFilter = ColorFilter.tint(White),
                painter = painterResource(id = R.drawable.magic_logo),
                contentDescription = stringResource(R.string.logo_image_description),
            )
        }
    }
}

@Preview(name = "HomeMenu")
@Composable
fun HomeMenuPreview() {
    HomeMenu(
        menuItem = HomeMenuItem(
            type = MenuItemType.MAGIC_DEX,
            nameId = R.string.magic_dex,
            color = LightBlack
        ),
        onClick = { }
    )
}