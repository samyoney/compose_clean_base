package com.example.compose_clean_base.app.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.google.accompanist.insets.LocalWindowInsets
import androidx.compose.foundation.layout.width
import com.example.compose_clean_base.app.theme.*

@Composable
fun DefaultSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen4())
)

@Composable
fun ExtraSmallSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen6())
)

@Composable
fun SmallSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen12())
)

@Composable
fun MediumHeightSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen18())
)

@Composable
fun Width20Spacer() = Spacer(modifier = Modifier.width(dimen20()))

@Composable
fun LargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen24())
)

@Composable
fun ExtraLargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(dimen30())
)

@Composable
fun KeyboardSpacer(
    modifier: Modifier = Modifier,
    confirmHeight: (Dp) -> Dp = { it },
) {
    val imeVisible = LocalWindowInsets.current.ime.isVisible
    val imeHeight = with(LocalDensity.current) { LocalWindowInsets.current.ime.bottom.toDp() }
    val height by animateDpAsState(
        if (imeVisible) confirmHeight(imeHeight) else dimen0(),
        label = String()
    )
    Spacer(modifier.height(height))
}