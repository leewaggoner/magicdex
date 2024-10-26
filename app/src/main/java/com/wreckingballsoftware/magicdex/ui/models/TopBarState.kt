package com.wreckingballsoftware.magicdex.ui.models

import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.navigation.NavRoute

enum class TopBarState(
    @StringRes val title: Int,
    @StringRes val searchPlaceholder: Int,
    val hasBackButton: Boolean,
    val hasSearch: Boolean,
) {
    INVALID(title = 0, searchPlaceholder = 0, hasBackButton = false, hasSearch = false),
    CARDS(title = R.string.find_cards, searchPlaceholder = R.string.search_cards, hasBackButton = false, hasSearch = true),
    CARD_DETAIL(title = R.string.card_detail, searchPlaceholder = R.string.search_cards, hasBackButton = true, hasSearch = false),
    SET(title = R.string.find_sets, searchPlaceholder = R.string.search_sets, hasBackButton = false, hasSearch = true),
    TYPE(title = R.string.find_types, searchPlaceholder = R.string.search_types, hasBackButton = false, hasSearch = true),
    FORMAT(title = R.string.find_formats, searchPlaceholder = R.string.search_formats, hasBackButton = false, hasSearch = true);

    companion object {
        fun getCurrentTopBarState(route: NavRoute): TopBarState {
            return when (route) {
                NavRoute.Cards -> CARDS
                is NavRoute.CardDetail -> CARD_DETAIL
                NavRoute.Sets -> SET
                NavRoute.Types -> TYPE
                NavRoute.Formats -> FORMAT
                else -> INVALID
            }
        }
    }
}
