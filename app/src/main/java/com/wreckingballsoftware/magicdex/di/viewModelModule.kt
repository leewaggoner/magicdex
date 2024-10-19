package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.ui.magicscaffold.MagicScaffoldViewModel
import com.wreckingballsoftware.magicdex.ui.cards.CardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MagicScaffoldViewModel(
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
