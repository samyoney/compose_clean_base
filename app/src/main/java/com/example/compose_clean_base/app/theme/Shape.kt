package com.example.compose_clean_base.app.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
)

val mediumLargeShapes = RoundedCornerShape(19.dp)
val extraLargeShapes = RoundedCornerShape(25.dp)