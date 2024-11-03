package com.wreckingballsoftware.magicdex.ui.carddetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.extensions.inlineContent
import com.wreckingballsoftware.magicdex.extensions.withImagesToAnnotatedText
import com.wreckingballsoftware.magicdex.ui.components.ManaCost
import com.wreckingballsoftware.magicdex.ui.components.PowerCard
import com.wreckingballsoftware.magicdex.ui.models.MagicCardAboutData
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardAbout(
    modifier: Modifier = Modifier,
    cardData: MagicCardAboutData,
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
            text = cardData.name,
            style = MaterialTheme.magicTypography.titleMedium
        )
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding),
            text = cardData.type,
            style = MaterialTheme.magicTypography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding),
            text = cardData.rarity,
            style = MaterialTheme.magicTypography.body
        )
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingSmall),
                text = stringResource(id = R.string.mana),
                style = MaterialTheme.magicTypography.body
            )
            ManaCost(manaList = cardData.manaCost)
        }
        PowerCard(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimensions.padding,
                )
                .fillMaxWidth(),
            power = cardData.power,
            toughness = cardData.toughness
        )
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding)
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
                text = cardData.cmc.toString(),
                style = MaterialTheme.magicTypography.body
            )
        }
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding),
            text = stringResource(id = R.string.card_text),
            style = MaterialTheme.magicTypography.titleSmall
        )
        val cardText = cardData.text
        BasicText(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall),
            text = cardText.withImagesToAnnotatedText(),
            inlineContent = cardText.inlineContent(),
            style = MaterialTheme.magicTypography.body
        )
        if (cardData.flavor.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimensions.padding),
                text = stringResource(id = R.string.flavor_text),
                style = MaterialTheme.magicTypography.titleSmall
            )
            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimensions.paddingSmall),
                text = cardData.flavor,
                style = MaterialTheme.magicTypography.body
            )
        }
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding),
            text = stringResource(id = R.string.set),
            style = MaterialTheme.magicTypography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.paddingSmall),
            text = cardData.setName,
            style = MaterialTheme.magicTypography.body
        )
    }
}

@Preview(name = "CardAbout")
@Composable
fun CardAboutPreview() {
    CardAbout(
        cardData = MagicCardAboutData(
            name = "Archangel Avacyn",
            type = "Legendary Creature — Angel",
            manaCost = listOf("{3}", "{W}", "{W}"),
            cmc = 5.0,
            text = "Flash\nFlying, vigilance\n{W} When Archangel Avacyn enters the battlefield, {T} creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
            flavor = "A golden helix streaked skyward from the Helvault. A thunderous explosion shattered the silver monolith and Avacyn emerged, free from her prison at last.",
            power = "4",
            toughness = "4",
            rarity = "Mythic Rare",
            setName = "10th Edition",
        )
    )
}