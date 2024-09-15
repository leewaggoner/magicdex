package com.wreckingballsoftware.magicdex.ui.home.components.models

typealias NewsList = List<News>

data class News(
    val title: String = "",
    val link: String = "",
    val date: String = "",
    val imageUrl: String = "",
)
