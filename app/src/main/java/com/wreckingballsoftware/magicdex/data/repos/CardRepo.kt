package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.MagicCards
import com.wreckingballsoftware.magicdex.data.network.CardService
import io.ktor.client.call.body

class CardRepo(
    private val cardService: CardService
) {
    suspend fun getCards(startIndex: Int, limit: Int): MagicCards {
        return cardService.getCards(startIndex, limit).body<MagicCards>()
    }
}
