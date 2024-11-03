package com.wreckingballsoftware.magicdex.di

import org.koin.dsl.module

val appModule = module {
    includes(networkModule, viewModelModule, repoModule, useCaseModule)
}