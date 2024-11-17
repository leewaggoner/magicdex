package com.wreckingballsoftware.magicdex.ui.carddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardAbout
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardArt
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardMisc
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailEvents
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailState
import com.wreckingballsoftware.magicdex.ui.components.CardDetailTab
import com.wreckingballsoftware.magicdex.ui.components.NoTouchCircularProgress
import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import org.koin.androidx.compose.getViewModel

@Composable
fun CardDetailScreen(
    viewModel: CardDetailViewModel = getViewModel(),
) {
    Column {
        CardDetailContent(
            state = viewModel.state,
            onEvent = viewModel::onEvent,
        )
    }

    if (viewModel.state.showProgress) {
        NoTouchCircularProgress()
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardDetailContent(
    state: CardDetailState,
    onEvent: (CardDetailEvents) -> Unit,
) {
    CardDetailTab(
        tabs = state.tabs,
        selected = state.selected,
        onClick = { tab -> onEvent(CardDetailEvents.OnTabSelected(tab)) }
    )
    when (state.selected) {
        DetailTab.ART -> {
            CardArt(
                artData = state.artData,
            )
        }
        DetailTab.ABOUT -> {
            CardAbout(
                aboutData = state.aboutData,
            )
        }
        DetailTab.MISC -> {
            CardMisc(
                miscData = state.miscData,
            )
        }
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
