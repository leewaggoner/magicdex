package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wreckingballsoftware.magicdex.extensions.mapManaToResource
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
            Image(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.paddingVeryTiny)
                    .size(MaterialTheme.dimensions.magicCardManaSize),
                painter = painterResource(id = mana.mapManaToResource()),
                contentDescription = mana,
            )
        }
    }
}
