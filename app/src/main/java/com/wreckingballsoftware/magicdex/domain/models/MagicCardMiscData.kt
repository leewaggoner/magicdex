package com.wreckingballsoftware.magicdex.domain.models

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.Legalities
import com.wreckingballsoftware.magicdex.data.models.Rulings

data class MagicCardMiscData(
    val name: String = "",
    val rulings: List<Rulings> = emptyList(),
    val legalities: List<Legalities> = emptyList(),
)

fun Card.mapToMagicCardMiscData(): MagicCardMiscData {
    return MagicCardMiscData(
        name = name ?: "",
        rulings = rulings ?: emptyList(),
        legalities = legalities ?: emptyList(),
    )
}
