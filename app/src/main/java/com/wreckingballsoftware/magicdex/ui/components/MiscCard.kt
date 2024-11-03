package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun MiscCard(
    date: String,
    ruling: String,
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.dimensions.padding)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.dimensions.padding)
            ) {
                Text(
                    text = date,
                    style = MaterialTheme.magicTypography.bodyBold
                )
                Text(
                    modifier = Modifier.padding(top = MaterialTheme.dimensions.paddingSmall),
                    text = ruling,
                    style = MaterialTheme.magicTypography.body
                )
            }
        }
    }
}

@Preview(name = "RulingCard")
@Composable
fun RulingCardPreview() {
    MaterialTheme {
        MiscCard(
            date = "2016-04-08",
            ruling = "Archangel Avacyn’s delayed triggered ability triggers at the beginning of the next upkeep regardless of whose turn it is."
        )
    }
}
