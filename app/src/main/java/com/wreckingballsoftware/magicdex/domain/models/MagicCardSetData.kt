package com.wreckingballsoftware.magicdex.domain.models

import com.wreckingballsoftware.magicdex.data.models.Set

data class MagicCardSetData(
    var border: String,
    var name: String,
    var onlineOnly : Boolean,
    var releaseDate: String,
    var type: String
)

fun Set.mapToMagicCardSetData(): MagicCardSetData {
    return MagicCardSetData(
        border = this.border?.replaceFirstChar { it.uppercase() } ?: "",
        name = this.name ?: "",
        onlineOnly = this.onlineOnly ?: false,
        releaseDate = this.releaseDate ?: "",
        type = this.type?.replaceFirstChar { it.uppercase() } ?: "",
    )
}
