package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.components.MagicDexErrorAlert
import com.wreckingballsoftware.magicdex.ui.components.NoTouchCircularProgress
import com.wreckingballsoftware.magicdex.ui.magicdex.components.CardItem
import com.wreckingballsoftware.magicdex.ui.magicdex.models.MagicDexEvent
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun MagicDexScreen(
    navGraph: NavGraph,
    viewModel: MagicDexViewModel = getViewModel(),
) {
    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
        }
    }

    val cardList by viewModel.cards.collectAsStateWithLifecycle()

    MagicDexScreenContent(cardList)

    viewModel.state.alertMessage?.let { message ->
        MagicDexErrorAlert(
            onDismissRequest = {
                viewModel.onEvent(MagicDexEvent.DismissDialog)
            },
            message = message,
            confirmAction = {
                viewModel.onEvent(MagicDexEvent.DismissDialog)
            }
        )
    }

    if (viewModel.state.isLoading) {
        NoTouchCircularProgress()
    }
}

@Composable
private fun MagicDexScreenContent(
    cards: List<Card>
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(cards) { card ->
                    CardItem(
                        card = card,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview(name = "Magic Dex Screen")
@Composable
private fun MagicDexScreenPreview() {
    MagicDexScreenContent(
        cards = listOf(
            Card(
                name = "Ancestor's Chosen",
                colorIdentity = listOf("W"),
                type = "Creature \u2014 Human Cleric",
                rarity = "Uncommon",
                number = "1",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130550&type=card",
            ),
            Card(
                name = "Academy Researchers",
                colorIdentity = listOf("U"),
                type = "Creature \u2014 Human Wizard of a type that doesn't exist on this plane",
                rarity = "Uncommon",
                number = "63",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132072&type=card",
            ),
            Card(
                name = "Prodigal Pyromancer",
                colorIdentity = listOf("R"),
                type = "Creature \u2014 Human Wizard",
                rarity = "Common",
                number = "221",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=134752&type=card",
            ),
            Card(
                name = "Vampire Bats",
                colorIdentity = listOf("B"),
                type = "Creature \u2014 Bat",
                rarity = "Common",
                number = "186",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=135195&type=card",
            ),
            Card(
                name = "Forest",
                colorIdentity = listOf("G"),
                type = "Basic Land \u2014 Forest",
                rarity = "Common",
                number = "380",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129559&type=card",
            ),
            Card(
                name = "The Animus",
                colorIdentity = emptyList(),
                type = "Legendary Artifact",
                rarity = "Rare",
                number = "69",
                imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129552&type=card",
            ),
        )
    )
}