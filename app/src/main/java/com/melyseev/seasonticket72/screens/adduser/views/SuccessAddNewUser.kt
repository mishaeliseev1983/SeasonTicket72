package com.melyseev.seasonticket72.screens.adduser.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.adduser.model.AddUserViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme


@Composable
fun SuccessAddNewUser(
    modifier: Modifier = Modifier,
    state: AddUserViewState.SuccessDisplay,
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
                    imageVector = Icons.Filled.CheckCircle,
                    tint = JetDanceTheme.colors.controlColor,
                    contentDescription = "Accepted Icon"
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.success_add),
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )
                /*
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text =  state.new_name,
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )

                 */

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
