package com.wreckingballsoftware.magicdex.ui.models

import androidx.compose.ui.graphics.Color
import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.manaImageNames
import com.wreckingballsoftware.magicdex.data.models.uiColor

data class MagicCardItemData(
    val id: String = "",
    val name: String = "",
    val colorIdentity: Color = Color.Transparent,
    val type: String = "",
    val rarity: String = "",
    val manaCost: List<String> = emptyList(),
    val power: String = "",
    val toughness: String = "",
    val number: String = "",
    val imageUrl: String = "",
)

fun Card.mapToMagicCardItemData(): MagicCardItemData {
    return MagicCardItemData(
        id = this.id ?: "",
        name = this.name ?: "",
        colorIdentity = this.uiColor(),
        type = this.type ?: "",
        rarity = this.rarity ?: "",
        manaCost = this.manaImageNames(),
        power = this.power ?: "",
        toughness = this.toughness ?: "",
        number = this.number ?: "",
        imageUrl = this.imageUrl ?: "",
    )
}