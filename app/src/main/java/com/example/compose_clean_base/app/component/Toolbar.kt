package com.example.compose_clean_base.app.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.compose_clean_base.app.theme.PurpleBgColor
import com.example.compose_clean_base.app.theme.HeaderPinkColor
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.dimen8

@Composable
fun Toolbar(
    @StringRes titleResId: Int,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = AppTypography.h6,
                color = PurpleBgColor
            )
        },
        backgroundColor = HeaderPinkColor,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}

@Composable
fun ToolbarWithBackNav(
    @StringRes titleResId: Int,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = AppTypography.h6,
                color = PurpleBgColor
            )
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = PurpleBgColor,
                modifier = Modifier
                    .padding(dimen8())
                    .clickable { pressOnBack.invoke() }
            )
        },
        backgroundColor = HeaderPinkColor,
        modifier = Modifier.fillMaxWidth()
    )
}