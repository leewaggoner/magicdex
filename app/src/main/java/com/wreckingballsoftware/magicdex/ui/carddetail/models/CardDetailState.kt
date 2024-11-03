package com.wreckingballsoftware.magicdex.ui.carddetail.models

import com.wreckingballsoftware.magicdex.data.models.Card
import com.wreckingballsoftware.magicdex.ui.models.DetailTab

data class CardDetailState(
    val card: Card? = null,
    val message: String? = null,
    val showProgress: Boolean = true,
    val tabs: List<DetailTab> = emptyList(),
    val selected: DetailTab = DetailTab.ABOUT
)
