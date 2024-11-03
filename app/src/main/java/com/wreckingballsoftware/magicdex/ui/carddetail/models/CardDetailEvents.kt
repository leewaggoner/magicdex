package com.wreckingballsoftware.magicdex.ui.carddetail.models

import com.wreckingballsoftware.magicdex.ui.models.DetailTab

sealed interface CardDetailEvents {
    data class OnTabSelected(val tab: DetailTab) : CardDetailEvents
}