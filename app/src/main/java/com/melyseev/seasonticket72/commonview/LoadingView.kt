package com.melyseev.seasonticket72.screens.commonviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.melyseev.seasonticket72.theme.JetDanceTheme

 @Composable
 fun LoadingView() {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(JetDanceTheme.colors.primaryBackground)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = JetDanceTheme.colors.tintColor
            )
        }
}