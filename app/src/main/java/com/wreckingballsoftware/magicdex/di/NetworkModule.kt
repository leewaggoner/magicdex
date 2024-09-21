package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.data.network.CardService
import com.wreckingballsoftware.magicdex.data.network.httpClientAndroid
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single { provideHttpClient() }
    single { CardService(get()) }
}

fun provideHttpClient(): HttpClient {
    return httpClientAndroid
}
