package com.wreckingballsoftware.magicdex.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MagicCards (
    var cards: List<Card>
)

@Serializable
data class Card (
    var name: String?,
    var names: List<String>?,
    var manaCost: String?,
    var cmc: Double?,
    var colors: List<String>?,
    var colorIdentity: List<String>?,
    var type: String?,
    var supertypes: List<String>?,
    var types: List<String>?,
    var subtypes: List<String>?,
    var rarity: String?,
    var set: String?,
    var setName: String?,
    var text: String?,
    var flavor: String?, //not in the API
    var artist: String?,
    var number: String?,
    var power: String?,
    var toughness: String?,
    var layout: String?,
    var multiverseid: Int?,
    var imageUrl: String?,
    var variations: List<String>?,
    var rulings: List<Rulings>?,
    var foreignNames: List<ForeignNames>?,
    var printings:List<String>?,
    var originalText: String?,
    var originalType: String?,
    var legalities: List<Legalities>?, //not in the API
    var id: String?
)

@Serializable
data class Rulings (
    var date: String?,
    var text: String?
)

@Serializable
data class ForeignNames (
    var name: String?,
    var text: String?, //not in the API
    var flavor: String?, //not in the API
    var imageUrl: String?, //not in the API
    var language: String?,
    var multiverseid: Int?
)

@Serializable
data class Legalities (
    var format: String?,
    var legality: String?
)
