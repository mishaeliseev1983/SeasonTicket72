package com.melyseev.seasonticket72.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class JetDanceColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color
)

data class JetDanceTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

data class JetDanceShape(
    val padding: Dp,
    val cornersStyle: Shape
)

data class JetDanceImage(
    val mainIcon: Int,
    val mainIconDescription: String
)

object JetDanceTheme {
    val colors: JetDanceColors
        @Composable
        get() = LocalJetDanceColors.current

    val typography: JetDanceTypography
        @Composable
        get() = LocalJetDanceTypography.current

    val shapes: JetDanceShape
        @Composable
        get() = LocalJetDanceShape.current

    val images: JetDanceImage
        @Composable
        get() = LocalJetDanceImage.current
}

enum class JetDanceStyle {
    Purple, Orange, Blue, Red, Green
}

enum class JetDanceSize {
    Small, Medium, Big
}

enum class JetDanceCorners {
    Flat, Rounded
}

val LocalJetDanceColors = staticCompositionLocalOf<JetDanceColors> {
    error("No colors provided")
}

val LocalJetDanceTypography = staticCompositionLocalOf<JetDanceTypography> {
    error("No font provided")
}

val LocalJetDanceShape = staticCompositionLocalOf<JetDanceShape> {
    error("No shapes provided")
}

val LocalJetDanceImage = staticCompositionLocalOf<JetDanceImage> {
    error("No images provided")
}