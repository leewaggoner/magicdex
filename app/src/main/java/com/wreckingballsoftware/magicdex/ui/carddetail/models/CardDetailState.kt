package com.wreckingballsoftware.magicdex.ui.carddetail.models

import com.wreckingballsoftware.magicdex.data.models.Card

data class CardDetailState(
    val card: Card? = null,
    val message: String? = null,
    val showProgress: Boolean = true,
    val tabs: List<Int> = emptyList(),
    val selected: Int = 0
)
