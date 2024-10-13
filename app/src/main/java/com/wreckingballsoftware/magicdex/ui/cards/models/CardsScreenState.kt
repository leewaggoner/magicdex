package com.wreckingballsoftware.magicdex.ui.cards.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardsScreenState(
    val isLoading: Boolean = false,
    val alertMessage: String? = null,
) : Parcelable
