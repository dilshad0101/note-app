package com.app.note.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.note.R

// Set of Material typography styles to start with

val Nunito = FontFamily(Font(R.font.nunito_regular,FontWeight(400)),Font(R.font.nunito_bold, FontWeight.SemiBold))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight(500),
        fontSize =22.sp,
        letterSpacing = 0.2.sp
    ),
    h1 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp),

    caption = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight(400),
        letterSpacing = 0.3.sp,
        fontSize = 35.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight(350),
        fontSize = 25.sp,
        letterSpacing = 0.2.sp
    ),
)