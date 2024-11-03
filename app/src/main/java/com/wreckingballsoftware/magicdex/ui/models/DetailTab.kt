package com.wreckingballsoftware.magicdex.ui.models

import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.R

enum class DetailTab(
    @StringRes val titleId: Int
) {
    ABOUT(R.string.about),
    ART(R.string.art),
    MISC(R.string.misc)
}
