package com.melyseev.seasonticket72.screens.sellticket.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme

@Composable
fun SellViewMessage(
    modifier: Modifier = Modifier,
    state: SellViewState.DisplayMessage,
    onCloseClick: () -> Unit) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.CenterHorizontally),
                    imageVector = if(state.success)Icons.Filled.CheckCircle else Icons.Filled.Error,
                    tint = JetDanceTheme.colors.controlColor,
                    contentDescription = "Accepted Icon"
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = if(state.success)stringResource(id = R.string.success) else stringResource(id = R.string.error),
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text =  "${state.message}",
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )

                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = onCloseClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = JetDanceTheme.colors.controlColor,
                        disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                            alpha = 0.3f
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.action_close),
                        style = JetDanceTheme.typography.body,
                        color = Color.White
                    )
                }
            }
        }
    }
}