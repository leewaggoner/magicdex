package com.wreckingballsoftware.magicdex.di

import com.wreckingballsoftware.magicdex.ui.home.HomeViewModel
import com.wreckingballsoftware.magicdex.ui.magicdex.MagicDexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(
            handle = get(),
        )
    }

    viewModel {
        MagicDexViewModel(
            handle = get(),
        )
    }
}