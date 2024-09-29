package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchQueryChanged: (String) -> Unit,
    searchAction: () -> Unit,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimensions.appBarHeight)
                .background(
                    color = LightBlack,
                    shape = RoundedCornerShape(
                        bottomStart = MaterialTheme.dimensions.appBarCorner,
                        bottomEnd = MaterialTheme.dimensions.appBarCorner,
                    )
                )
                .clip(
                    RoundedCornerShape(
                        bottomStart = MaterialTheme.dimensions.appBarCorner,
                        bottomEnd = MaterialTheme.dimensions.appBarCorner,
                    )
                ),
        ),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.dimensions.padding),
            text = stringResource(R.string.app_bar_title),
            style = MaterialTheme.magicTypography.titleLarge,
            color = White,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.dimensions.appBarCorner)),
            colors = CardDefaults.cardColors(
                containerColor = LightBlack
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimensions.padding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val focusManager = LocalFocusManager.current
                TextField(
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                focusManager.clearFocus()
                                searchAction()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.search_icon_description),
                            )
                        }
                    },
                    singleLine = true,
                    value = searchQuery,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.app_bar_hint)
                        )
                    },
                    onValueChange = { searchQueryChanged(it) },
                    shape = RoundedCornerShape(MaterialTheme.dimensions.searchCorner),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White,
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                            searchAction()
                        }
                    )
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .size(MaterialTheme.dimensions.appBarImageSize)
                .align(Alignment.TopEnd)
                .offset(
                    x = MaterialTheme.dimensions.appBarImageOffset,
                    y = -MaterialTheme.dimensions.appBarImageOffset
                )
                .alpha(0.1f),
            painter = painterResource(id = R.drawable.magic_logo),
            contentDescription = stringResource(R.string.logo_image_description),
        )
    }
}

@Preview(name = "HomeTopAppBar")
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar(
        searchQuery = "",
        searchQueryChanged = { },
        searchAction = { },
    )
}
