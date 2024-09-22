package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.wreckingballsoftware.magicdex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicDexErrorAlert(
    onDismissRequest: () -> Unit,
    message: String,
    confirmAction: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(R.string.error)) },
        text = { Text(message) },
        confirmButton = {
            Button(
                onClick = {
                    confirmAction()
                    onDismissRequest()
                }
            ) {
                Text("OK")
            }
        }
    )
}
