package com.wreckingballsoftware.magicdex.ui.carddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailEvents
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailState
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun CardDetailScreen(
    navGraph: NavGraph,
    viewModel: CardDetailViewModel = getViewModel(),
) {
    Column {
        CardDetailContent(
            state = viewModel.state,
            onEvent = viewModel::onEvent,
        )
    }
}

@Composable
private fun CardDetailContent(
    state: CardDetailState,
    onEvent: (CardDetailEvents) -> Unit,
) {
    Column {
        Text(text = state.card?.name ?: "")
    }
}

@Preview(name = "CardDetailContent")
@Composable
fun CardDetailContentPreview() {
    CardDetailContent(
        state = CardDetailState(),
        onEvent = { },
    )
}