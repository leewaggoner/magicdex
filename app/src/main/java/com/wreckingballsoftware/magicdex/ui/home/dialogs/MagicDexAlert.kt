package com.wreckingballsoftware.magicdex.ui.home.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicDexAlert(
    onDismissRequest: () -> Unit,
    message: String,
    confirmAction: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("I'm an alert!") },
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
