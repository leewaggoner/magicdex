package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.models.MagicCards
import com.wreckingballsoftware.magicdex.data.network.ApiResult
import com.wreckingballsoftware.magicdex.data.network.CardService
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepo(
    private val cardService: CardService
) {
    fun getCards(page: Int): Flow<ApiResult<List<Card>>> = flow {
        emit(ApiResult.Loading)
        kotlin.runCatching { cardService.getCards(page) }
            .onSuccess {
                emit(ApiResult.Success(it.body<MagicCards>().cards))
            }
            .onFailure { ex ->
                emit(ApiResult.Error(ex as Exception))
            }
    }
}
