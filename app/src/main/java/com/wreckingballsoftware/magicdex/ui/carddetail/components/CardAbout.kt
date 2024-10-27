package com.wreckingballsoftware.magicdex.ui.carddetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.manaImageNames
import com.wreckingballsoftware.magicdex.ui.components.ManaCost
import com.wreckingballsoftware.magicdex.ui.components.PowerCard
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardAbout(
    modifier: Modifier = Modifier,
    card: Card,
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
            text = card.name ?: "",
            style = MaterialTheme.magicTypography.titleMedium
        )
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall),
            text = stringResource(id = R.string.mana_cost),
            style = MaterialTheme.magicTypography.titleSmall
        )
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingSmall),
                text = stringResource(id = R.string.mana),
                style = MaterialTheme.magicTypography.body
            )
            ManaCost(manaList = card.manaImageNames())
        }
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingSmall),
                text = stringResource(id = R.string.cmc),
                style = MaterialTheme.magicTypography.body,
            )
            Text(
                text = card.cmc.toString(),
            )
        }
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall),
            text = stringResource(id = R.string.card_text),
            style = MaterialTheme.magicTypography.titleSmall
        )
        Text(
            text = card.text ?: "",
        )
        PowerCard(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimensions.padding,
                    bottom = MaterialTheme.dimensions.padding,
                )
                .fillMaxWidth(),
            power = card.power ?: "",
            toughness = card.toughness ?: ""
        )
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.types),
            style = MaterialTheme.magicTypography.titleSmall
        )
    }
}

@Preview(name = "CardAbout")
@Composable
fun CardAboutPreview() {
    CardAbout(
        card = Card(
            name = "Card Name",
            manaCost = "{W}{U}{B}{R}{G}",
            cmc = 5.0,
            text = "Flash\nFlying, vigilance\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
            power = "4",
            toughness = "4",
        )
    )
}