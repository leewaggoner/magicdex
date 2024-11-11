package com.wreckingballsoftware.magicdex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val SET_URL = "https://api.magicthegathering.io/v1/sets"

class SetService(private val httpClient: HttpClient) {
    suspend fun getSets(
        startIndex: Int,
        limit: Int,
        query: String
    ) = httpClient.get(urlString = SET_URL) {
        url {
            parameters.append(name = "name", value = query)
            parameters.append(name = "page", value = startIndex.toString())
            parameters.append(name = "pageSize", value = limit.toString())
        }
    }
}
