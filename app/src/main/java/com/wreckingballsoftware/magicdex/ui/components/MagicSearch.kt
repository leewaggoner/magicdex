package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicSearch(
    query: String,
    placeholder: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
) {
    var focusManager = LocalFocusManager.current

    SearchBar(
        modifier = Modifier,
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChanged,
                onSearch = onSearch,
                expanded = false,
                onExpandedChange = { },
                placeholder = { Text(text = placeholder) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { focusManager.clearFocus() },
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                focusManager.clearFocus()
                                onClear()
                            },
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                    )
                },
                colors = TextFieldDefaults.colors(),
            )
        },
        expanded = false,
        onExpandedChange = { },
        shape = SearchBarDefaults.inputFieldShape,
        colors = SearchBarDefaults.colors(),
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content =  { },
    )
}

@Preview(name = "MagicSearch")
@Composable
private fun MagicSearchPreview(
    @PreviewParameter(MagicSearchPreviewProvider::class) params: SearchParams
) {
    MagicSearch(
        query = params.query,
        placeholder = params.placeholder,
        onQueryChanged = { },
        onSearch = { },
        onClear = { },
    )
}

private data class SearchParams(
    val query: String = "",
    val placeholder: String = "",
)

private class MagicSearchPreviewProvider : PreviewParameterProvider<SearchParams> {
    override val values = sequenceOf(
        SearchParams(),
        SearchParams(placeholder = "New Search here"),
        SearchParams(query = "New Query", placeholder = "New Search here"),
    )
}
