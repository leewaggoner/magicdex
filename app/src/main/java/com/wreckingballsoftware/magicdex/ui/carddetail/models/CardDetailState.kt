package com.wreckingballsoftware.magicdex.ui.carddetail.models

import com.wreckingballsoftware.magicdex.ui.models.DetailTab
import com.wreckingballsoftware.magicdex.ui.models.MagicCardAboutData
import com.wreckingballsoftware.magicdex.ui.models.MagicCardArtData
import com.wreckingballsoftware.magicdex.ui.models.MagicCardMiscData

data class CardDetailState(
    val aboutData: MagicCardAboutData = MagicCardAboutData(),
    val artData: MagicCardArtData = MagicCardArtData(),
    val miscData: MagicCardMiscData = MagicCardMiscData(),
    val message: String? = null,
    val showProgress: Boolean = true,
    val tabs: List<DetailTab> = emptyList(),
    val selected: DetailTab = DetailTab.ABOUT
)
