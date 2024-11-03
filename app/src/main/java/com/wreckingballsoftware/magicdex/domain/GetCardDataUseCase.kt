package com.wreckingballsoftware.magicdex.domain

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.data.network.ApiResult
import com.wreckingballsoftware.magicdex.data.repos.CardRepo
import com.wreckingballsoftware.magicdex.ui.models.MagicCardAboutData
import com.wreckingballsoftware.magicdex.ui.models.MagicCardArtData
import com.wreckingballsoftware.magicdex.ui.models.MagicCardMiscData
import com.wreckingballsoftware.magicdex.ui.models.mapToMagicCardAboutData
import com.wreckingballsoftware.magicdex.ui.models.mapToMagicCardArtData
import com.wreckingballsoftware.magicdex.ui.models.mapToMagicCardMiscData

class GetCardDataUseCase(
    private val cardRepo: CardRepo,
) {
    var card: Card = Card()
    suspend fun getCardDataById(id: String): String? {
        var errorMessage: String? = null
        when (val result = cardRepo.getCardById(id)) {
            is ApiResult.Success -> card = result.data
            is ApiResult.Error -> errorMessage = result.exception.message ?: "Unknown error"
            is ApiResult.Loading -> { }
        }
        return errorMessage
    }

    fun getCardAboutData(): MagicCardAboutData {
        return card.mapToMagicCardAboutData()
    }

    fun getCardArtData(): MagicCardArtData {
        return card.mapToMagicCardArtData()
    }

    fun getCardMiscData(): MagicCardMiscData {
        return card.mapToMagicCardMiscData()
    }
}