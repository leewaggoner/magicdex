package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.MagicCards
import com.wreckingballsoftware.magicdex.data.network.ApiResult
import com.wreckingballsoftware.magicdex.data.network.CardService
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

class CardRepo(
    private val cardService: CardService
) {
    suspend fun getCards(startIndex: Int, limit: Int, query: String): MagicCards {
        return cardService.getCards(
            startIndex,
            limit,
            query,
        ).body<MagicCards>()
    }

    suspend fun getCardById(cardId: String): ApiResult<Card> {
        val card = try {
            cardService.getCardById(cardId)
        } catch (e: Exception) {
            return ApiResult.Error(e)
        }
        return if (card.status == HttpStatusCode.OK && card.body<MagicCards>().cards.isNotEmpty()) {
            ApiResult.Success(card.body<MagicCards>().cards[0])
        } else {
            ApiResult.Error(Exception("Error getting card"))
        }
    }
}
