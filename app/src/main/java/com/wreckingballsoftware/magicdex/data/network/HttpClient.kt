package com.wreckingballsoftware.magicdex.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

private const val NETWORK_TIME_OUT = 6_000L

@OptIn(ExperimentalSerializationApi::class)
val httpClientAndroid = HttpClient(Android) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
                explicitNulls = false
            }
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_TIME_OUT
        connectTimeoutMillis = NETWORK_TIME_OUT
        socketTimeoutMillis = NETWORK_TIME_OUT
    }

    HttpResponseValidator {
        validateResponse { response: HttpResponse ->
            val statusCode = response.status.value

            println("HTTP status: $statusCode")

            when (statusCode) {
                400 -> throw ClientRequestException(response, "We could not process that action")
                403 -> throw ClientRequestException(response, "You exceeded the rate limit")
                404 -> throw ClientRequestException(response, "The requested resource could not be found")
                500 -> throw ServerResponseException(response, "We had a problem with our server. Please try again later")
                503 -> throw ServerResponseException(response, "We are temporarily offline for maintenance. Please try again later")
                in 300..399 -> throw RedirectResponseException(response, "Redirect response")
                in 400..499 -> throw ClientRequestException(response, "Client request exception")
                in 500..599 -> throw ServerResponseException(response, "Server response exception")
            }

            if (statusCode >= 600) {
                throw ResponseException(response, "Response exception")
            }
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, Json)
    }

    defaultRequest {
        contentType(Json)
        accept(Json)
    }
}
