package com.wreckingballsoftware.magicdex.ui.carddetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.data.models.Legalities
import com.wreckingballsoftware.magicdex.data.models.Rulings
import com.wreckingballsoftware.magicdex.ui.components.LegalityCard
import com.wreckingballsoftware.magicdex.ui.components.RulingCard
import com.wreckingballsoftware.magicdex.domain.models.MagicCardMiscData
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardMisc(
    modifier: Modifier = Modifier,
    miscData: MagicCardMiscData
) {
    Column(
        modifier = modifier.then(
            Modifier
                .padding(MaterialTheme.dimensions.padding)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        )
    ) {
        Text(
            text = miscData.name,
            style = MaterialTheme.magicTypography.titleMedium
        )
        Column(
            modifier = Modifier.padding(top = MaterialTheme.dimensions.padding)
        ) {
            if (miscData.rulings.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.rulings),
                    style = MaterialTheme.magicTypography.titleSmall
                )
                miscData.rulings.forEach { ruling ->
                    RulingCard(
                        title = ruling.date ?: "",
                        text = ruling.text ?: ""
                    )
                }
            }
            if (miscData.legalities.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.legalities),
                    style = MaterialTheme.magicTypography.titleSmall
                )
                miscData.legalities.forEach { legality ->
                    LegalityCard(
                        title = legality.format ?: "",
                        text = legality.legality ?: ""
                    )
                }
            }
        }
    }
}

@Preview(name = "CardMisc")
@Composable
fun CardMiscPreview() {
    MaterialTheme {
        CardMisc(
            miscData = MagicCardMiscData(
                name = "Card Name",
                rulings = listOf(
                    Rulings(
                        date = "2021-01-01",
                        text = "This is a ruling"
                    )
                ),
                legalities = listOf(
                    Legalities(
                        format = "Standard",
                        legality = "Legal"
                    )
                )
            )
        )
    }
}
