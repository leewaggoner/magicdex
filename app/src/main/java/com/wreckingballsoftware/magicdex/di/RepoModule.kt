package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.data.repos.CardPagingSource
import com.wreckingballsoftware.magicdex.data.repos.CardRepo
import com.wreckingballsoftware.magicdex.data.repos.SetPagingSource
import com.wreckingballsoftware.magicdex.data.repos.SetRepo
import org.koin.dsl.module

val repoModule = module {
    single { CardRepo(get()) }
    single { CardPagingSource(get()) }
    single { SetRepo(get()) }
    single { SetPagingSource(get()) }
}
