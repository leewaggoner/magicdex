package com.wreckingballsoftware.magicdex.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MagicCards (
    var cards: List<Card> = listOf(),
)

@Serializable
data class Card (
    var name: String? = "",
    var names: List<String>? = listOf(),
    var manaCost: String? = "",
    var cmc: Double? = -1.0,
    var colors: List<String>? = listOf(),
    var colorIdentity: List<String>? = listOf(),
    var type: String? = "",
    var supertypes: List<String>? = listOf(),
    var types: List<String>? = listOf(),
    var subtypes: List<String>? = listOf(),
    var rarity: String? = "",
    var set: String? = "",
    var setName: String? = "",
    var text: String? = "",
    var flavor: String? = "", //not in the API
    var artist: String? = "",
    var number: String? = "",
    var power: String? = "",
    var toughness: String? = "",
    var layout: String? = "",
    var multiverseid: Int? = -1,
    var imageUrl: String? = "",
    var variations: List<String>? = listOf(),
    var rulings: List<Rulings>? = listOf(),
    var foreignNames: List<ForeignNames>? = listOf(),
    var printings:List<String>? = listOf(),
    var originalText: String? = "",
    var originalType: String? = "",
    var legalities: List<Legalities>? = listOf(), //not in the API
    var id: String? = "",
)

@Serializable
data class Rulings (
    var date: String? = "",
    var text: String? = "",
)

@Serializable
data class ForeignNames (
    var name: String? = "",
    var text: String? = "", //not in the API
    var flavor: String? = "", //not in the API
    var imageUrl: String? = "", //not in the API
    var language: String? = "",
    var multiverseid: Int? = -1,
)

@Serializable
data class Legalities (
    var format: String? = "",
    var legality: String? = "",
)
