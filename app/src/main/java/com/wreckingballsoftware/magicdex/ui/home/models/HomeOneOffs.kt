package com.wreckingballsoftware.magicdex.ui.home.models

sealed interface HomeOneOffs {
    data object OnGoToMagicDex : HomeOneOffs
    data class OnShowToast(val message: String) : HomeOneOffs
}