package com.wreckingballsoftware.magicdex.ui.carddetail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardAbout
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardArt
import com.wreckingballsoftware.magicdex.ui.carddetail.components.CardMisc
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailEvents
import com.wreckingballsoftware.magicdex.ui.carddetail.models.CardDetailState
import com.wreckingballsoftware.magicdex.ui.components.CardDetailTab
import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.theme.LightWhite
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardDetailContent(
    state: CardDetailState,
    onEvent: (CardDetailEvents) -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
        )
    )

    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MaterialTheme.dimensions.sheetPeekHeight,
        sheetContainerColor = LightWhite,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CardDetailTab(
                    tabs = state.tabs,
                    selected = state.selected,
                    onClick = { tab -> onEvent(CardDetailEvents.OnTabSelected(tab)) }
                )
                when (state.selected) {
                    DetailTab.ABOUT -> {
                        CardAbout(
                            aboutData = state.aboutData,
                        )
                    }
                    DetailTab.ART -> {
                        CardArt(
                            artData = state.artData,
                        )
                    }
                    DetailTab.MISC -> {
                        CardMisc(
                            miscData = state.miscData,
                        )
                    }
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding)
                .padding(horizontal = MaterialTheme.dimensions.padding)
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = state.artData.imageUrl,
                contentDescription = state.artData.name,
                placeholder = painterResource(id = R.drawable.card_back),
                error = painterResource(id = R.drawable.card_back),
                onError = { error ->
                    Log.e("CardDetailScreen", "error: $error")
                }
            )
            Text(
                modifier = Modifier.padding(top = MaterialTheme.dimensions.paddingSmall),
                text = stringResource(id = R.string.artist, state.artData.artist),
                style = MaterialTheme.magicTypography.label,
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
