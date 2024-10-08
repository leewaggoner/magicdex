package com.wreckingballsoftware.magicdex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CardService(private val httpClient: HttpClient) {
    suspend fun getCards(startIndex: Int, limit: Int) = httpClient.get(
        "https://api.magicthegathering.io/v1/cards"
    ) {
        url {
            parameters.append("page", startIndex.toString())
            parameters.append("pageSize", limit.toString())
        }
    }
}