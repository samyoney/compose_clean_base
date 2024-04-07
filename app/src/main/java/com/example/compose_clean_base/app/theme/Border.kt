package com.example.compose_clean_base.app.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.unit.dp

val InputTextBorder = BorderStroke(
    width = 1.dp,
    color = BorderButtonColor
)

val LoginTextFieldBorder = BorderStroke(
    width = 1.dp,
    UnSelectedColor
)

val SelectorBorder = BorderStroke(
    width = 2.dp,
    color = PurpleBgColor
)

val UnSelectorBorder = BorderStroke(
    width = 2.dp,
    color = BorderButtonColor
)

val UnSelectorDiaryBorder = BorderStroke(
    width = 2.dp,
    color = BorderButtonColor.copy(alpha = 0.5F)
)