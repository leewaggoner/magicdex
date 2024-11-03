package com.wreckingballsoftware.magicdex.ui.models

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.manaImageNames

data class MagicCardAboutData(
    val name: String = "",
    val type: String = "",
    val manaCost: List<String> = emptyList(),
    val cmc: Double = 0.0,
    val text: String = "",
    val flavor: String = "",
    val power: String = "",
    val toughness: String = "",
    val rarity: String = "",
    val setName: String = "",
)

fun Card.mapToMagicCardAboutData(): MagicCardAboutData {
    return MagicCardAboutData(
        name = name ?: "",
        type = type ?: "",
        rarity = rarity ?: "",
        manaCost = manaImageNames(),
        cmc = cmc ?: 0.0,
        text = text ?: "",
        power = power ?: "",
        toughness = toughness ?: "",
        flavor = flavor ?: "",
        setName = setName ?: "",
    )
}