package com.wreckingballsoftware.magicdex.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.cards.components.CardItem
import com.wreckingballsoftware.magicdex.ui.cards.models.CardsScreenEvent
import com.wreckingballsoftware.magicdex.ui.components.MagicDexErrorAlert
import com.wreckingballsoftware.magicdex.ui.components.NoTouchCircularProgress
import com.wreckingballsoftware.magicdex.ui.models.mapToMagicCardItemData
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun CardsScreen(
    navGraph: NavGraph,
    viewModel: CardsViewModel = getViewModel(),
) {
    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
        }
    }

    val cardList = viewModel.cards.collectAsLazyPagingItems()

    MagicDexScreenContent(
        cards = cardList,
        onEvent = viewModel::onEvent,
    )

    viewModel.state.alertMessage?.let { message ->
        MagicDexErrorAlert(
            onDismissRequest = { viewModel.onEvent(CardsScreenEvent.DismissDialog) },
            message = message,
            confirmAction = { viewModel.onEvent(CardsScreenEvent.DismissDialog) },
        )
    }
}

@Composable
private fun MagicDexScreenContent(
    cards: LazyPagingItems<Card>,
    onEvent: (CardsScreenEvent) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (cards.loadState.refresh) {
                is androidx.paging.LoadState.Loading -> {
                    NoTouchCircularProgress()
                }
                is androidx.paging.LoadState.Error -> {
                    val e = cards.loadState.refresh as androidx.paging.LoadState.Error
                    onEvent(CardsScreenEvent.ApiError(e.error.localizedMessage ?: "Unknown error"))
                }
                else -> {
                    LazyColumn {
                        items(
                            count = cards.itemCount,
                            key = cards.itemKey { card -> card.id ?: UUID.randomUUID().toString() }
                        ) { index ->
                            cards[index]?.let { card ->
                                CardItem(
                                    card = card.mapToMagicCardItemData(),
                                    onClick = { }
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
private fun MagicDexScreenPreview() {
    val cardData = providePreviewData()
    val pagingData = PagingData.from(cardData)
    val cardDataFlow = MutableStateFlow(pagingData)
    MagicDexScreenContent(
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