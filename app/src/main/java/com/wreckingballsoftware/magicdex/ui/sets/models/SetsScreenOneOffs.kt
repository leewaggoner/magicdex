package com.wreckingballsoftware.magicdex.ui.sets.models

sealed interface SetsScreenOneOffs {
    data class ShowError(val message: String) : SetsScreenOneOffs
}
