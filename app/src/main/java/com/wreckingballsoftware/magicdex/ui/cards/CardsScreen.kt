package com.wreckingballsoftware.magicdex.ui.cards

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
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenEvent
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenOneOffs
import com.wreckingballsoftware.magicdex.ui.components.CardItem
import com.wreckingballsoftware.magicdex.ui.models.mapToMagicCardItemData
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.theme.Blue
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun CardsScreen(
    navGraph: NavGraph,
    searchQuery: String,
    viewModel: CardsViewModel = getViewModel(),
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
            is CardsScreenOneOffs.NavigateToCardDetail -> navGraph.navigateToCardDetailScreen(nav.cardId)
            is CardsScreenOneOffs.ShowError -> {
                snackScope.launch {
                    snackState.showSnackbar(nav.message)
                }
            }
        }
    }

    viewModel.onEvent(CardsScreenEvent.Search(searchQuery))

    val cardList = viewModel.cards.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CardsScreenContent(
            cards = cardList,
            onEvent = viewModel::onEvent,
        )
        SnackbarHost(hostState = snackState)
        when {
            cardList.loadState.refresh is androidx.paging.LoadState.Loading -> {
                CircularProgressIndicator(color =  Blue)
            }
            cardList.loadState.append is androidx.paging.LoadState.Loading -> {
                CircularProgressIndicator(color =  Blue)
            }
        }
    }
}

@Composable
private fun CardsScreenContent(
    cards: LazyPagingItems<Card>,
    onEvent: (CardsScreenEvent) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.dimensions.padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (cards.loadState.refresh) {
                is androidx.paging.LoadState.Error -> {
                    val e = cards.loadState.refresh as androidx.paging.LoadState.Error
                    onEvent(CardsScreenEvent.ApiError(e.error.localizedMessage ?: "Unknown error"))
                }
                else -> {
                    if (cards.itemCount == 0) {
                        Text(
                            text = stringResource(R.string.no_cards_found),
                            style = MaterialTheme.magicTypography.body,
                        )
                    }
                    LazyColumn {
                        items(
                            count = cards.itemCount,
                            key = cards.itemKey { card -> card.id ?: UUID.randomUUID().toString() }
                        ) { index ->
                            cards[index]?.let { card ->
                                CardItem(
                                    card = card.mapToMagicCardItemData(),
                                    onClick = { onEvent(CardsScreenEvent.OnCardSelected(card.id ?: "")) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Magic Dex Screen")
@Composable
private fun CardsScreenPreview() {
    val cardData = providePreviewData()
    val pagingData = PagingData.from(cardData)
    val cardDataFlow = MutableStateFlow(pagingData)
    CardsScreenContent(
        cards = cardDataFlow.collectAsLazyPagingItems(),
        onEvent = { }
    )
}

private fun providePreviewData() = listOf(
    Card(
        id = UUID.randomUUID().toString(),
        name = "Ancestor's Chosen",
        colorIdentity = listOf("W"),
        type = "Creature \u2014 Human Cleric",
        rarity = "Uncommon",
        number = "1",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130550&type=card",
    ),
    Card(
        id = UUID.randomUUID().toString(),
        name = "Academy Researchers",
        colorIdentity = listOf("U"),
        type = "Creature \u2014 Human Wizard of a type that doesn't exist on this plane",
        rarity = "Uncommon",
        number = "63",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132072&type=card",
    ),
    Card(
        id = UUID.randomUUID().toString(),
        name = "Prodigal Pyromancer",
        colorIdentity = listOf("R"),
        type = "Creature \u2014 Human Wizard",
        rarity = "Common",
        number = "221",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=134752&type=card",
    ),
    Card(
        id = UUID.randomUUID().toString(),
        name = "Vampire Bats",
        colorIdentity = listOf("B"),
        type = "Creature \u2014 Bat",
        rarity = "Common",
        number = "186",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=135195&type=card",
    ),
    Card(
        id = UUID.randomUUID().toString(),
        name = "Forest",
        colorIdentity = listOf("G"),
        type = "Basic Land \u2014 Forest",
        rarity = "Common",
        number = "380",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129559&type=card",
    ),
    Card(
        id = UUID.randomUUID().toString(),
        name = "The Animus",
        colorIdentity = emptyList(),
        type = "Legendary Artifact",
        rarity = "Rare",
        number = "69",
        imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129552&type=card",
    ),
)
