package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.MagicCards
import com.wreckingballsoftware.magicdex.data.network.CardService
import io.ktor.client.call.body
import kotlinx.coroutines.flow.flow

class CardRepo(
    private val cardService: CardService
) {
    fun getCardsFlow(page: Int) = flow<Result<List<Card>>> {
        kotlin.runCatching { cardService.getCards(page) }
            .onSuccess { response ->
                emit(Result.success(response.body<MagicCards>().cards))
            }
            .onFailure { ex ->
                emit(Result.failure(ex))
            }
        }
}
