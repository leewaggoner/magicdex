package com.wreckingballsoftware.magicdex.ui.sets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.data.models.Set
import com.wreckingballsoftware.magicdex.domain.models.mapToMagicCardSetData
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.components.SetItem
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.sets.models.SetsScreenEvent
import com.wreckingballsoftware.magicdex.ui.sets.models.SetsScreenOneOffs
import com.wreckingballsoftware.magicdex.ui.theme.Blue
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun SetsScreen(
    navGraph: NavGraph,
    searchQuery: String,
    viewModel: SetsViewModel = getViewModel(),
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
            is SetsScreenOneOffs.ShowError -> {
                snackScope.launch {
                    snackState.showSnackbar(nav.message)
                }
            }
        }
    }

    viewModel.onEvent(SetsScreenEvent.Search(searchQuery))

    val setList = viewModel.sets.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        SetsScreenContent(
            sets = setList,
            onEvent = viewModel::onEvent,
        )
        SnackbarHost(hostState = snackState)
        when {
            setList.loadState.refresh is androidx.paging.LoadState.Loading -> {
                CircularProgressIndicator(color =  Blue)
            }
            setList.loadState.append is androidx.paging.LoadState.Loading -> {
                CircularProgressIndicator(color =  Blue)
            }
        }
    }
}

@Composable
private fun SetsScreenContent(
    sets: LazyPagingItems<Set>,
    onEvent: (SetsScreenEvent) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.dimensions.padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (sets.loadState.refresh) {
                is androidx.paging.LoadState.Error -> {
                    val e = sets.loadState.refresh as androidx.paging.LoadState.Error
                    onEvent(SetsScreenEvent.ApiError(e.error.localizedMessage ?: "Unknown error"))
                }
                else -> {
                    if (sets.itemCount == 0) {
                        Text(
                            text = stringResource(R.string.no_cards_found),
                            style = MaterialTheme.magicTypography.body,
                        )
                    }
                    LazyColumn {
                        items(
                            count = sets.itemCount,
                            key = sets.itemKey { set -> set.code ?: UUID.randomUUID().toString() }
                        ) { index ->
                            sets[index]?.let { set ->
                                SetItem(
                                    Modifier.padding(vertical = MaterialTheme.dimensions.padding),
                                    set = set.mapToMagicCardSetData(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Sets Screen")
@Composable
fun SetsScreenPreview() {
    val setData = providePreviewData()
    val pagingData = PagingData.from(setData)
    val setDataFlow = MutableStateFlow(pagingData)
    SetsScreenContent(
        sets = setDataFlow.collectAsLazyPagingItems(),
        onEvent = { }
    )
}

private fun providePreviewData() = listOf(
    Set(
        code = UUID.randomUUID().toString(),
        name = "Tenth Edition",
        border = "Black",
        type = "Expansion",
        onlineOnly = false,
        releaseDate = "2003-06-06",
    ),
    Set(
        code = UUID.randomUUID().toString(),
        name = "Double Masters 2022",
        border = "Black",
        type = "Expansion",
        onlineOnly = true,
        releaseDate = "2003-06-06",
    ),
    Set(
        code = UUID.randomUUID().toString(),
        name = "30th Anniversary Edition",
        type = "Expansion",
        onlineOnly = false,
        releaseDate = "2003-06-06",
    ),
)
