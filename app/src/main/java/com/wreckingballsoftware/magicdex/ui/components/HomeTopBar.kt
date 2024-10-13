package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    title: String,
    hasSearch: Boolean,
    query: String,
    placeholder: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onBack: (() -> Unit)?,
) {
    TopAppBar(
        modifier = modifier.then(
            Modifier
                .height(if (hasSearch) MaterialTheme.dimensions.topBarHeight else MaterialTheme.dimensions.topBarHeightNoSearch)
        ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LightBlack
        ),
        navigationIcon = {
            if (onBack != null) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = onBack),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = White,
                    contentDescription = null,
                )
            }
        },
        title = {
            Box {
                Image(
                    modifier = Modifier
                        .size(size = MaterialTheme.dimensions.topBarImageSize)
                        .align(alignment = Alignment.TopEnd)
                        .offset(
                            x = MaterialTheme.dimensions.topBarImageOffsetX,
                            y = -MaterialTheme.dimensions.topBarImageOffsetY
                        )
                        .alpha(0.1f),
                    painter = painterResource(id = R.drawable.magic_logo),
                    contentDescription = stringResource(R.string.logo_image_description),
                )
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title.ifEmpty { "" },
                        color = White,
                    )
                    if (hasSearch) {
                        MagicSearch(
                            query = query,
                            placeholder = placeholder,
                            onQueryChanged = onQueryChanged,
                            onSearch = onSearch,
                            onClear = onClear,
                        )
                    }
                }
            }
        },
    )
}

@Preview(name = "HomeTopBar")
@Composable
private fun HomeTopBarPreview(
    @PreviewParameter(HomeTopBarParamsPreviewProvider::class) params: HomeTopBarParams
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HomeTopBar(
            title = params.title,
            hasSearch = params.hasSearch,
            query = params.query,
            placeholder = params.placeholder,
            onQueryChanged = { },
            onSearch = { },
            onClear = { },
            onBack = params.onBack,
        )
    }
}

private data class HomeTopBarParams(
    val title: String = "",
    val hasSearch: Boolean = false,
    val query: String = "",
    val placeholder: String = "",
    val onBack: (() -> Unit)? = null,
)

private class HomeTopBarParamsPreviewProvider : PreviewParameterProvider<HomeTopBarParams> {
    override val values = sequenceOf(
        HomeTopBarParams(),
        HomeTopBarParams(
            title = "Search for card",
            hasSearch = true,
            placeholder = "Search for something",
        ),
        HomeTopBarParams(
            title = "Search for set",
            hasSearch = true,
            placeholder = "Search for something",
            onBack = { },
        ),
        HomeTopBarParams(
            title = "Search for type",
            hasSearch = true,
            query = "Creature",
            placeholder = "Search for something",
        ),
        HomeTopBarParams(
            title = "Card Details",
            onBack = { },
        ),
    )
}
