package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.home.components.MagicDexErrorAlert
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

    var cardList = emptyList<Card>()
    val cardResponse = viewModel.cardList.collectAsStateWithLifecycle()
    cardResponse.value
        .onSuccess { list ->
            cardList = list
        }
        .onFailure { ex ->
            viewModel.onEvent(MagicDexEvent.ApiError(ex))
        }

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
}

@Composable
fun MagicDexScreenContent(
    cards: List<Card>
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LazyColumn {
                items(cards) { card ->
                    if (card.name != null) {
                        Text(text = card.name ?: "")
                    }
                }
            }
        }
    }
}

@Preview(name = "Magic Dex Screen")
@Composable
fun MagicDexScreenPreview() {
    MagicDexScreenContent(
        cards = listOf(
            Card(name = "Card 1"),
            Card(name = "Card 2"),
            Card(name = "Card 3"),
        )
    )
}