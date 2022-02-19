package com.melyseev.seasonticket72.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melyseev.seasonticket72.R


@Composable
fun MainTheme(
    style: JetDanceStyle = JetDanceStyle.Purple,
    textSize: JetDanceSize = JetDanceSize.Medium,
    paddingSize: JetDanceSize = JetDanceSize.Medium,
    corners: JetDanceCorners = JetDanceCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                JetDanceStyle.Purple -> purpleDarkPalette
                JetDanceStyle.Blue -> blueDarkPalette
                JetDanceStyle.Orange -> orangeDarkPalette
                JetDanceStyle.Red -> redDarkPalette
                JetDanceStyle.Green -> greenDarkPalette
            }
        }
        false -> {
            when (style) {
                JetDanceStyle.Purple -> purpleLightPalette
                JetDanceStyle.Blue -> blueLightPalette
                JetDanceStyle.Orange -> orangeLightPalette
                JetDanceStyle.Red -> redLightPalette
                JetDanceStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = JetDanceTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                JetDanceSize.Small -> 24.sp
                JetDanceSize.Medium -> 28.sp
                JetDanceSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                JetDanceSize.Small -> 14.sp
                JetDanceSize.Medium -> 16.sp
                JetDanceSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                JetDanceSize.Small -> 14.sp
                JetDanceSize.Medium -> 16.sp
                JetDanceSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                JetDanceSize.Small -> 10.sp
                JetDanceSize.Medium -> 12.sp
                JetDanceSize.Big -> 14.sp
            }
        )
    )

    val shapes = JetDanceShape(
        padding = when (paddingSize) {
            JetDanceSize.Small -> 12.dp
            JetDanceSize.Medium -> 16.dp
            JetDanceSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            JetDanceCorners.Flat -> RoundedCornerShape(0.dp)
            JetDanceCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val images = JetDanceImage(
        mainIcon = if (darkTheme) R.drawable.ic_baseline_mood_24 else R.drawable.ic_baseline_mood_bad_24,
        mainIconDescription = if (darkTheme) "Good Mood" else "Bad Mood"
    )

    CompositionLocalProvider(
        LocalJetDanceColors provides colors,
        LocalJetDanceTypography provides typography,
        LocalJetDanceShape provides shapes,
        LocalJetDanceImage provides images,
        content = content
    )
}