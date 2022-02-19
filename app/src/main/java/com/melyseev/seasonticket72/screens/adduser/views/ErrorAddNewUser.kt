package com.melyseev.seasonticket72.screens.adduser.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.theme.JetDanceTheme


@Composable
fun ErrorAddNewUser(
    modifier: Modifier = Modifier,
    onErrorHappenedClick: () -> Unit,
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground,

    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                Text(
                    text = stringResource(id = R.string.error_add),
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )
                Button(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = onErrorHappenedClick,
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = JetDanceTheme.colors.errorColor,
                        disabledBackgroundColor = JetDanceTheme.colors.errorColor.copy(
                            alpha = 0.3f
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.Ok),
                        style = JetDanceTheme.typography.body,
                        color = Color.White
                    )
                    // }
                }
            }
        }
    }
}