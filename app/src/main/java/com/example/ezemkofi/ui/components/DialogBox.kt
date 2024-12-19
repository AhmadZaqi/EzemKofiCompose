package com.example.ezemkofi.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogBox(
    onDismissRequest: ()-> Unit,
    onConfirmation: ()-> Unit,
    title: String,
    message: String,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = "Yes")
            }
        },
        title = { Text(
            text = title,
            color = MaterialTheme.colorScheme.primary
        )
        },
        text = { Text(text = message) },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "Cancel")
            }
        }
    )
}