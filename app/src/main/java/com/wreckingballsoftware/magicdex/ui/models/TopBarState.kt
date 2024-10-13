package com.wreckingballsoftware.magicdex.ui.models

import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.R

enum class TopBarState(
    @StringRes val title: Int,
    @StringRes val searchPlaceholder: Int,
    val hasBackButton: Boolean,
    val hasSearch: Boolean,
) {
    CARD(title = R.string.find_cards, searchPlaceholder = R.string.search_cards, hasBackButton = false, hasSearch = true),
    SET(title = R.string.find_sets, searchPlaceholder = R.string.search_sets, hasBackButton = false, hasSearch = true),
    TYPE(title = R.string.find_types, searchPlaceholder = R.string.search_types, hasBackButton = false, hasSearch = true),
    FORMAT(title = R.string.find_formats, searchPlaceholder = R.string.search_formats, hasBackButton = false, hasSearch = true)
}
