package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.MagicCards
import com.wreckingballsoftware.magicdex.data.network.CardService
import io.ktor.client.call.body

class CardRepo(
    private val cardService: CardService
) {
    suspend fun getCards(page: Int): Result<List<Card>> {
        return kotlin.runCatching { cardService.getCards(page).body<MagicCards>().cards }
    }
}
