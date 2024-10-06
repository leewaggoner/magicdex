package com.wreckingballsoftware.magicdex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CardService(private val httpClient: HttpClient) {
    suspend fun getCards(page: Int) = httpClient.get(
        "https://api.magicthegathering.io/v1/cards"
    ) {
        url {
            parameters.append("page", page.toString())
            parameters.append("pageSize", "20")
        }
    }
}