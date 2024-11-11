package com.wreckingballsoftware.magicdex.data.repos

import com.wreckingballsoftware.magicdex.data.models.MagicSets
import com.wreckingballsoftware.magicdex.data.network.SetService
import io.ktor.client.call.body

class SetRepo(
    private val setService: SetService
) {
    suspend fun getSets(startIndex: Int, limit: Int, query: String): MagicSets {
        return setService.getSets(
            startIndex,
            limit,
            query,
        ).body<MagicSets>()
    }
}
