package com.wreckingballsoftware.magicdex.ui.carddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            navGraph = navGraph,
        )
    }
}

@Composable
private fun CardDetailContent(
    state: CardDetailState,
    onEvent: (CardDetailEvents) -> Unit,
    navGraph: NavGraph,
) {
    Column {
        Text(text = state.card?.name ?: "")
    }
}