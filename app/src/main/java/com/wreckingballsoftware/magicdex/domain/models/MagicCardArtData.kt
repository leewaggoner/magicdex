package com.wreckingballsoftware.magicdex.domain.models

import com.wreckingballsoftware.magicdex.data.models.Card

data class MagicCardArtData(
    val name: String = "",
    val imageUrl: String = "",
    val artist: String = "",
)

fun Card.mapToMagicCardArtData(): MagicCardArtData {
    return MagicCardArtData(
        name = name ?: "",
        imageUrl = imageUrl ?: "",
        artist = artist ?: "",
    )
}
