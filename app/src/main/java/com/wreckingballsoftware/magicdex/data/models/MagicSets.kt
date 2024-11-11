package com.wreckingballsoftware.magicdex.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MagicSets (
    var sets: List<Set> = listOf(),
)

@Serializable
data class Set(
    var block: String? = "",
    var border: String? = "",
    var code: String? = "",
    var name: String? = "",
    var onlineOnly : Boolean? = false,
    var releaseDate: String? = "",
    var type: String? = "",
)
