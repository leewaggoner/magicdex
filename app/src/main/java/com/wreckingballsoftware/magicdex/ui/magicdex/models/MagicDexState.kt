package com.wreckingballsoftware.magicdex.ui.magicdex.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MagicDexState(
    val isLoading: Boolean = false,
    val alertMessage: String? = null,
) : Parcelable
