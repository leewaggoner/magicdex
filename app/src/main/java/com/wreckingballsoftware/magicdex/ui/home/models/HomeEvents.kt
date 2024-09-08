package com.wreckingballsoftware.magicdex.ui.home.models

sealed interface HomeEvents {
    data class OnSearchQueryChanged(val query: String) : HomeEvents
    data object OnSearchAction : HomeEvents
//    data object OnMagicDexClick : HomeEvents
//    data object OnToastClick : HomeEvents
//    data object OnAlertDialogClick : HomeEvents
}
