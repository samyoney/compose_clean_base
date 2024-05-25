package com.example.compose_clean_base.presentation.login.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.compose_clean_base.app.theme.AppShapes
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.BaseColor
import com.example.compose_clean_base.app.theme.PurpleBgColor
import com.example.compose_clean_base.app.theme.dimen38
import com.example.compose_clean_base.app.theme.dimen54

@Composable
fun LoginButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PurpleBgColor,
            contentColor = BaseColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimen54())
            .padding(horizontal = dimen38())
            .clip(AppShapes.small)
    ) {
        Text(
            title,
            style = AppTypography.button
        )
    }
}