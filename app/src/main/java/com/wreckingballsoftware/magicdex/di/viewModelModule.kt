package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.ui.home.HomeViewModel
import com.wreckingballsoftware.magicdex.ui.cards.CardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            handle = get(),
        )
    }

    viewModel {
        CardsViewModel(
            pagingSource = get(),
            handle = get(),
        )
    }
}
