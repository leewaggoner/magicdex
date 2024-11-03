package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.extensions.inlineContent
import com.wreckingballsoftware.magicdex.extensions.withImagesToAnnotatedText
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun RulingCard(
    title: String,
    text: String,
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.dimensions.padding)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = White,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            ),
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.dimensions.padding)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.magicTypography.bodyBold
                )
                BasicText(
                    modifier = Modifier.padding(top = MaterialTheme.dimensions.paddingSmall),
                    text = text.withImagesToAnnotatedText(),
                    inlineContent = text.inlineContent(),
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
        RulingCard(
            title = "2016-04-08",
            text = "Archangel Avacyn’s delayed triggered ability triggers at the beginning of the next upkeep regardless of whose turn it is."
        )
    }
}
