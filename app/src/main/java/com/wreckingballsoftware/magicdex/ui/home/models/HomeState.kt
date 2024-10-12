package com.wreckingballsoftware.magicdex.ui.home.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeState(
    val searchQuery: String = "",
    val searchPlaceholder: String = "Search for a card",
    val hasBackButton: Boolean = false,
) : Parcelable
