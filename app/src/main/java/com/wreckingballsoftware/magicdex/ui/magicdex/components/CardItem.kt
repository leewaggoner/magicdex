package com.wreckingballsoftware.magicdex.ui.magicdex.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.uiColor
import com.wreckingballsoftware.magicdex.ui.components.Pill
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: Card,
    onClick: (Card) -> Unit,
) {
    Card(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(MaterialTheme.dimensions.padding)
                .clickable { onClick(card) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = card.uiColor(),
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.dimensions.padding)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.dimensions.paddingSmall)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = card.name ?: "",
                    style = MaterialTheme.magicTypography.titleOnDarkSmall
                )
                if(!card.number.isNullOrEmpty()) {
                    Text(
                        text = "#${card.number}",
                        style = MaterialTheme.magicTypography.titleOnDarkSmall
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = MaterialTheme.dimensions.padding)
                        .weight(1.0f),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Pill(
                        modifier = Modifier.padding(bottom = MaterialTheme.dimensions.paddingTiny),
                        text = card.type ?: ""
                    )
                    Pill(text = card.rarity ?: "")
                }
                AsyncImage(
                    modifier = Modifier
                        .height(120.dp),
                    model = card.imageUrl,
                    placeholder = painterResource(id = R.drawable.card_back),
                    error = painterResource(id = R.drawable.card_back),
                    contentDescription = card.name ?: "",
                    onError = { error ->
                        Log.e("CardItem", "error: $error")
                    }
                )
            }
        }
    }
}

@Preview(name = "Card Item")
@Composable
private fun CardItemPreview(
    @PreviewParameter(CardItemPreviewData::class) card: Card
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardItem(
            card = card,
            onClick = {}
        )
    }
}

private class CardItemPreviewData : PreviewParameterProvider<Card> {
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
            type = "Creature \u2014 Human Wizard of a type that doesn't exist on this plane",
            rarity = "Uncommon",
            number = "63",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132072&type=card",
        ),
        Card(
            name = "Prodigal Pyromancer",
            colorIdentity = listOf("R"),
            type = "Creature \u2014 Human Wizard",
            rarity = "Common",
            number = "221",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=134752&type=card",
        ),
        Card(
            name = "Vampire Bats",
            colorIdentity = listOf("B"),
            type = "Creature \u2014 Bat",
            rarity = "Common",
            number = "186",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=135195&type=card",
        ),
        Card(
            name = "Forest",
            colorIdentity = listOf("G"),
            type = "Basic Land \u2014 Forest",
            rarity = "Common",
            number = "380",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129559&type=card",
        ),
        Card(
            name = "The Animus",
            colorIdentity = emptyList(),
            type = "Legendary Artifact",
            rarity = "Rare",
            number = "69",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129552&type=card",
        ),
    )
}