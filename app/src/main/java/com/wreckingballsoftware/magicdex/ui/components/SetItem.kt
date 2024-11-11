package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.domain.models.MagicCardSetData
import com.wreckingballsoftware.magicdex.ui.theme.Black
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun SetItem(
    modifier: Modifier = Modifier,
    set: MagicCardSetData,
) {
    Card(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimensions.magicSetCardHeight)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Black,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.set_image),
                contentDescription = set.name,
                contentScale = ContentScale.Crop,
                alpha = 0.3f,
                modifier = Modifier.fillMaxSize(),
            )
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = set.name,
                    style = MaterialTheme.magicTypography.titleOnDarkSmall,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.release_date, set.releaseDate),
                        style = MaterialTheme.magicTypography.bodyBoldOnDark,
                    )
                    if (set.onlineOnly) {
                        Text(
                            text = stringResource(R.string.online_only),
                            style = MaterialTheme.magicTypography.bodyBoldOnDark,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.expansion_set, set.type),
                        style = MaterialTheme.magicTypography.bodyBoldOnDark,
                    )
                    if (set.border.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.border, set.border),
                            style = MaterialTheme.magicTypography.bodyBoldOnDark,
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Set Item")
@Composable
private fun SetItemPreview() {
    SetItem(
        set = MagicCardSetData(
            border = "Black",
            name = "Ancestor's Chosen",
            onlineOnly = true,
            releaseDate = "2014-09-26",
            type = "Expansion",
        )
    )
}
