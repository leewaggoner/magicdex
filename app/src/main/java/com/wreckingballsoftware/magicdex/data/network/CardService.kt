package com.wreckingballsoftware.magicdex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val CARD_URL = "https://api.magicthegathering.io/v1/cards"

class CardService(private val httpClient: HttpClient) {
    suspend fun getCards(
        startIndex: Int,
        limit: Int,
        query: String
    ) = httpClient.get(urlString = CARD_URL) {
        url {
            parameters.append(name = "name", value = query)
            parameters.append(name = "page", value = startIndex.toString())
            parameters.append(name = "pageSize", value = limit.toString())
        }
    }

    suspend fun getCardById(cardId: String) = httpClient.get(urlString = CARD_URL) {
        url {
            parameters.append(name = "id", value = cardId)
        }
    }
}