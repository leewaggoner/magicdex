package com.wreckingballsoftware.magicdex.ui.models

import androidx.annotation.StringRes
import com.wreckingballsoftware.magicdex.R

enum class DetailTab(
    @StringRes val titleId: Int
) {
    ART(R.string.art),
    ABOUT(R.string.about),
    MISC(R.string.misc)
}
