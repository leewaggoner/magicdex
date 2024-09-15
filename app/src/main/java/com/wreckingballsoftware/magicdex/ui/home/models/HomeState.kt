package com.wreckingballsoftware.magicdex.ui.home.models

import com.wreckingballsoftware.magicdex.ui.home.components.models.NewsList

data class HomeState(
    val searchQuery: String = "",
    val newsList: NewsList = listOf(),
)
