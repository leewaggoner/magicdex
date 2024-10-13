package com.wreckingballsoftware.magicdex.ui.home.models

import android.os.Parcelable
import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeScreenState(
    @StringRes val title: Int = R.string.find_cards,
    val hasSearch: Boolean = true,
    val searchQuery: String = "",
    @StringRes val searchPlaceholder: Int = R.string.search_cards,
    val hasBackButton: Boolean = false,
) : Parcelable
