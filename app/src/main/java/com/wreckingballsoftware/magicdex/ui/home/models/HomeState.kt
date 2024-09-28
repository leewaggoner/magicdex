package com.wreckingballsoftware.magicdex.ui.home.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeState(
    val searchQuery: String = "",
) : Parcelable
