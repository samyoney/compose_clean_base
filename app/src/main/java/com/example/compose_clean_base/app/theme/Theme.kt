package com.example.compose_clean_base.app.theme

import androidx.compose.material.*
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    val defaultColorPalette = lightColors(primary = BaseColor)

    MaterialTheme(
        colors = defaultColorPalette,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}

class NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0f,
        focusedAlpha = 0f,
        hoveredAlpha = 0f,
        pressedAlpha = 0f,
    )
}