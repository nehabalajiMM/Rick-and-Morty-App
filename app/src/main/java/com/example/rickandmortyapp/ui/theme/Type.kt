package com.example.rickandmortyapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.R

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val fontFamily = getGoogleFontFamily(
    name = "Inter",
    provider = provider,
    weights = listOf(
        FontWeight.Normal,
        FontWeight.Bold,
        FontWeight.ExtraLight,
        FontWeight.SemiBold
    )
)

val AppFontTypography = Typography(
    defaultFontFamily = fontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = holo_blue_light
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = text_color_grey
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        color = holo_blue_light
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = holo_green_light
    )
)

@OptIn(ExperimentalTextApi::class)
private fun getGoogleFontFamily(
    name: String,
    provider: GoogleFont.Provider,
    weights: List<FontWeight>
): FontFamily {
    return FontFamily(
        weights.map {
            Font(GoogleFont(name), provider, it)
        }
    )
}
