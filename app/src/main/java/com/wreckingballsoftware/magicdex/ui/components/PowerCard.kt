package com.wreckingballsoftware.magicdex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.ui.theme.White
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun PowerCard(
    modifier: Modifier = Modifier,
    power: String,
    toughness: String,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
        )
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
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Power",
                        style = MaterialTheme.magicTypography.body,
                    )
                    Text(
                        text = power,
                        style = MaterialTheme.magicTypography.body,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Toughness",
                        style = MaterialTheme.magicTypography.body,
                    )
                    Text(
                        text = toughness,
                        style = MaterialTheme.magicTypography.body,
                    )
                }
            }
        }
    }
}

@Preview(name = "PowerCard")
@Composable
fun PowerCardPreview() {
    PowerCard(
        power = "2",
        toughness = "3"
    )
}