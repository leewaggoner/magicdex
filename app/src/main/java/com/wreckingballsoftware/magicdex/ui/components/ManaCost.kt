package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.theme.dimensions

@Composable
fun ManaCost(
    modifier: Modifier = Modifier,
    manaList: List<String>
) {
    Row(
        modifier = modifier.then(
            Modifier.padding(vertical = MaterialTheme.dimensions.paddingTiny)
        ),
    ) {
        manaList.forEach { mana ->
            val manaId: Int = if (LocalInspectionMode.current) {
                R.drawable.mana_w
            } else {
                val id = LocalContext.current.resources.getIdentifier(
                    mana,
                    "drawable",
                    LocalContext.current.packageName
                )
                if (id != 0) {
                    id
                } else {
                    R.drawable.mana_unexpected
                }
            }
            Image(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingVeryTiny)
                    .size(MaterialTheme.dimensions.magicCardManaSize),
                painter = painterResource(id = manaId),
                contentDescription = mana,
            )
        }
    }
}
