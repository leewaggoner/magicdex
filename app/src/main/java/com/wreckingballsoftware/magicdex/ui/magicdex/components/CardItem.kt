package com.wreckingballsoftware.magicdex.ui.magicdex.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.uiColor
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardItem(
    card: Card,
    onClick: (Card) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(card) },
        colors = CardDefaults.cardColors(
            containerColor = card.uiColor(),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.padding),
                text = card.name ?: "",
                style = MaterialTheme.magicTypography.homeMenu
            )
            Text(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.padding),
                text = "#${card.number ?: ""}",
                style = MaterialTheme.magicTypography.homeMenu
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.padding),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier
                        .padding(MaterialTheme.dimensions.padding),
                    text = card.type ?: "",
                    style = MaterialTheme.magicTypography.homeMenu
                )
                Text(
                    modifier = Modifier
                        .padding(MaterialTheme.dimensions.padding),
                    text = card.rarity ?: "",
                    style = MaterialTheme.magicTypography.homeMenu
                )
            }
        }
    }
}

@Preview(name = "Card Item")
@Composable
fun CardItemPreview(
    @PreviewParameter(CardItemPreviewData::class) card: Card
) {
    CardItem(
        card = card,
        onClick = {}
    )
}

class CardItemPreviewData : PreviewParameterProvider<Card> {
    override val values: Sequence<Card> = sequenceOf(
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
            type = "Creature \\u2014 Human Wizard",
            rarity = "Uncommon",
            number = "63",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132072&type=card",
        ),
        Card(
            name = "Prodigal Pyromancer",
            colorIdentity = listOf("R"),
            type = "Creature \\u2014 Human Wizard",
            rarity = "Common",
            number = "221",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=134752&type=card",
        ),
        Card(
            name = "Vampire Bats",
            colorIdentity = listOf("B"),
            type = "Creature \\u2014 Bat",
            rarity = "Common",
            number = "186",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=135195&type=card",
        ),
        Card(
            name = "Forest",
            colorIdentity = listOf("G"),
            type = "Basic Land \\u2014 Forest",
            rarity = "Common",
            number = "380",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129559&type=card",
        ),
    )
}