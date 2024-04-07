package com.example.compose_clean_base.app.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
        color = BaseTextColor
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 9.sp,
        letterSpacing = 0.1.sp,
        color = SubTitleColor
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,

        fontSize = 16.sp,
        color = TitleBlackColor
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        color = MainColor
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp,
        color = BaseTextColor
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
)

val diaryInputFieldText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W300,
    fontSize = 18.sp
)

val selectedButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = PurpleBgColor
)

val unSelectedButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BaseTextColor
)

val unSelectedDiaryButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = BaseTextColor.copy(alpha = 0.5F)
)

val painScoreLargeText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp,
    color = PurpleBgColor
)

val resultLargeText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 16.sp,
    color = PurpleBgColor
)

val resultMediumText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    color = PurpleBgColor
)

val resultSmallText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 13.sp,
    color = PurpleBgColor
)