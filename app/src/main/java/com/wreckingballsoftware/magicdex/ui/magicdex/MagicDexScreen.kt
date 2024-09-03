package com.wreckingballsoftware.magicdex.ui.magicdex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun MagicDexScreen(
    navGraph: NavGraph,
    viewModel: MagicDexViewModel = getViewModel(),
) {
    Column {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Magic Dex Screen")
        }
    }
}