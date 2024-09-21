package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun MagicDexScreen(
    navGraph: NavGraph,
    viewModel: MagicDexViewModel = getViewModel(),
) {
    MagicDexScreenContent()
}

@Composable
fun MagicDexScreenContent(
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Magic Dex Screen")
        }
    }
}

@Preview(name = "Magic Dex Screen")
@Composable
fun MagicDexScreenPreview() {
    MagicDexScreenContent()
}