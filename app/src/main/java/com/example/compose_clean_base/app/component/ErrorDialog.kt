package com.example.compose_clean_base.app.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.framework.extension.isNotNullOrBlank
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.theme.AppTypography

@Composable
fun ErrorDialog(
    title: String = String(),
    content: String = String(),
    dismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.wrapContentSize(),
        onDismissRequest = {
            dismiss()
        },
        title = if (title.isNotNullOrBlank()) {
            {
                Text(text = title)
            }
        } else null,
        text =
        if (content.isNotNullOrBlank()) {
            {
                Text(text = content)
            }
        } else null,
        confirmButton = {
            TextButton(
                onClick = {
                    dismiss()
                }
            ) {
                Text(style = AppTypography.body1, text = stringResource(R.string.ok))
            }
        }
    )
}


@Composable
fun AlertDialog(
    title: String = String(),
    content: String = String(),
    confirmLabel: String = String(),
    dismissLabel: String = String(),
    confirm: () -> Unit,
    dismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.wrapContentSize(),
        onDismissRequest = {
            dismiss()
        },
        title = if (title.isNotNullOrBlank()) {
            {
                Text(text = title)
            }
        } else null,
        text =
        if (content.isNotNullOrBlank()) {
            {
                Text(text = content)
            }
        } else null,
        dismissButton = {
            TextButton(
                onClick = {
                    dismiss()
                }
            ) {
                Text(style = AppTypography.body1, text = dismissLabel)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirm()
                }
            ) {
                Text(style = AppTypography.body1, text = confirmLabel)
            }
        }
    )
}
