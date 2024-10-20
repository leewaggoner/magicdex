package com.wreckingballsoftware.magicdex.ui.cards.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil.compose.AsyncImage
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.components.Pill
import com.wreckingballsoftware.magicdex.ui.models.MagicCardItemData
import com.wreckingballsoftware.magicdex.ui.theme.LightBlack
import com.wreckingballsoftware.magicdex.ui.theme.LightBlue
import com.wreckingballsoftware.magicdex.ui.theme.LightBrown
import com.wreckingballsoftware.magicdex.ui.theme.LightGreen
import com.wreckingballsoftware.magicdex.ui.theme.LightRed
import com.wreckingballsoftware.magicdex.ui.theme.LightWhite
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: MagicCardItemData,
    onClick: (String) -> Unit,
) {
    Card(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimensions.magicCardHeight)
                .padding(MaterialTheme.dimensions.padding)
                .clickable { onClick(card.id) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = card.colorIdentity,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.dimensions.padding)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.weight(1.0f),
            ) {
                val powerToughness = if (card.power.isNotEmpty() && card.toughness.isNotEmpty()) {
                    "${card.power}/${card.toughness}"
                } else {
                    ""
                }
                Text(
                    modifier = Modifier.padding(bottom = MaterialTheme.dimensions.paddingTiny),
                    text = "${card.name} $powerToughness",
                    style = MaterialTheme.magicTypography.titleOnDarkSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                ManaCost(manaList = card.manaCost)
                Spacer(modifier = Modifier.weight(1f))
                Pill(
                    modifier = Modifier.padding(bottom = MaterialTheme.dimensions.paddingTiny),
                    text = card.type
                )
                Pill(text = card.rarity)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                val cardNumber = if (card.number.isNotEmpty()) "#${card.number}" else ""
                Text(
                    modifier = Modifier.padding(bottom = MaterialTheme.dimensions.paddingTiny),
                    text = cardNumber,
                    style = MaterialTheme.magicTypography.titleOnDarkSmall
                )
                AsyncImage(
                    modifier = Modifier
                        .height(MaterialTheme.dimensions.magicCardImageHeight)
                        .clip(RoundedCornerShape(size = MaterialTheme.dimensions.magicCardCorner)),
                    model = card.imageUrl,
                    placeholder = painterResource(id = R.drawable.card_back),
                    error = painterResource(id = R.drawable.card_back),
                    contentDescription = card.name,
                    onError = { error ->
                        Log.e("CardItem", "error: $error")
                    }
                )
            }
        }
    }
}

@Composable
fun ManaCost(
    modifier: Modifier = Modifier,
    manaList: List<String>
) {
    Row(
        modifier = modifier.then(
            Modifier.padding(vertical = MaterialTheme.dimensions.paddingTiny)
        ),
    ) {
        manaList.forEach { mana ->
            var manaId = 0
            manaId = if (LocalInspectionMode.current) {
                R.drawable.mana_w
            } else {
                val id = LocalContext.current.resources.getIdentifier(
                    mana,
                    "drawable",
                    LocalContext.current.packageName
                )
                if (id != 0) {
                    id
                } else {
                    R.drawable.mana_unexpected
                }
            }
            Image(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingVeryTiny)
                    .size(MaterialTheme.dimensions.magicCardManaSize),
                painter = painterResource(id = manaId),
                contentDescription = mana,
            )
        }
    }
}

@Preview(name = "Card Item")
@Composable
private fun CardItemPreview(
    @PreviewParameter(CardItemPreviewData::class) card: MagicCardItemData
) {
    CardItem(
        card = card,
        onClick = {}
    )
}

private class CardItemPreviewData : PreviewParameterProvider<MagicCardItemData> {
    override val values: Sequence<MagicCardItemData> = sequenceOf(
        MagicCardItemData(
            name = "Ancestor's Chosen",
            colorIdentity = LightWhite,
            type = "Creature \u2014 Human Cleric",
            rarity = "Uncommon",
            manaCost = listOf("5", "w", "w"),
            power = "4",
            toughness = "6",
            number = "1",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130550&type=card",
        ),
        MagicCardItemData(
            name = "Academy Researchers",
            colorIdentity = LightBlue,
            type = "Creature \u2014 Human Wizard of a type that doesn't exist on this plane",
            rarity = "Uncommon",
            manaCost = listOf("5", "u", "u"),
            power = "4",
            toughness = "6",
            number = "63",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132072&type=card",
        ),
        MagicCardItemData(
            name = "Prodigal Pyromancer",
            colorIdentity = LightRed,
            type = "Creature \u2014 Human Wizard",
            rarity = "Common",
            manaCost = listOf("5", "r", "r"),
            power = "4",
            toughness = "6",
            number = "221",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=134752&type=card",
        ),
        MagicCardItemData(
            name = "Vampire Bats",
            colorIdentity = LightBlack,
            type = "Creature \u2014 Bat",
            rarity = "Common",
            manaCost = listOf("5", "b", "b"),
            power = "4",
            toughness = "6",
            number = "186",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=135195&type=card",
        ),
        MagicCardItemData(
            name = "Forest",
            colorIdentity = LightGreen,
            type = "Basic Land \u2014 Forest",
            rarity = "Common",
            manaCost = listOf("5", "g", "g"),
            number = "380",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129559&type=card",
        ),
        MagicCardItemData(
            name = "The Animus",
            colorIdentity = LightBrown,
            type = "Legendary Artifact",
            rarity = "Rare",
            manaCost = listOf("5"),
            power = "4",
            toughness = "6",
            number = "69",
            imageUrl = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=129552&type=card",
        ),
    )
}