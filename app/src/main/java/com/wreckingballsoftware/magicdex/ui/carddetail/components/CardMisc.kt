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
import com.wreckingballsoftware.magicdex.ui.components.MiscCard
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardMisc(
    modifier: Modifier = Modifier,
    rulings: List<Rulings>,
    legalities: List<Legalities>,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .padding(MaterialTheme.dimensions.padding)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        )
    ) {
        Column {
            if (rulings.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.rulings),
                    style = MaterialTheme.magicTypography.titleSmall
                )
                rulings.forEach { ruling ->
                    MiscCard(
                        date = ruling.date ?: "",
                        ruling = ruling.text ?: ""
                    )
                }
            }
            if (legalities.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.legalities),
                    style = MaterialTheme.magicTypography.titleSmall
                )
                legalities.forEach { legality ->
                    MiscCard(
                        date = legality.format ?: "",
                        ruling = legality.legality ?: ""
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
    }
}
