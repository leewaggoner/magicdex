package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.domain.GetCardDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCardDataUseCase(
            cardRepo = get()
        )
    }
}