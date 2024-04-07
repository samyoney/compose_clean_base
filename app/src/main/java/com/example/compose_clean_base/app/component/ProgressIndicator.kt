package com.example.compose_clean_base.app.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.compose_clean_base.app.controller.Delayed
import com.example.compose_clean_base.app.theme.LoadingBackgroundColor
import com.example.compose_clean_base.app.theme.dimen4
import com.example.compose_clean_base.app.theme.dimen48

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) =
    ProgressIndicator(
        modifier,
        dimen48(),
        dimen4()
    )

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    size: Dp = dimen48(),
    strokeWidth: Dp = dimen4(),
    color: Color = Color.White,
) {
    CircularProgressIndicator(modifier.size(size), color, strokeWidth)
}

private const val FULL_SCREEN_LOADING_DELAY = 100L

@Composable
fun FullScreenLoading(
    delayMillis: Long = FULL_SCREEN_LOADING_DELAY
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Delayed(delayMillis = delayMillis) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(LoadingBackgroundColor)
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null, enabled = true
                ) {
                }
        ) {
            ProgressIndicator()
        }
    }
}