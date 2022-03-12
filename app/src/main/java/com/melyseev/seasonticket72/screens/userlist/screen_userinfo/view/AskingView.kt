package com.melyseev.seasonticket72.screens.userlist.screen_userinfo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model.UserInfoViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme


@Composable
fun AskingView(
    modifier: Modifier = Modifier,
    state: UserInfoViewState.AskDeleteDisplay,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit) {
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
                    imageVector = Icons.Filled.RemoveCircle,
                    tint = JetDanceTheme.colors.controlColor,
                    contentDescription = "Accepted Icon"
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = " ${stringResource(id = R.string.action_ask_delete)} ${state.nameSurname} ?",
                    style = JetDanceTheme.typography.body,
                    color = JetDanceTheme.colors.primaryText
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {

                    Button(
                        modifier = Modifier
                            //.padding(top = 16.dp)
                            .height(48.dp),
                        onClick = onOkClick,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = JetDanceTheme.colors.controlColor,
                            disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.Ok),
                            style = JetDanceTheme.typography.body,
                            color = Color.White
                        )
                    }

                    Button(
                        modifier = Modifier
                            //.padding(top = 16.dp)
                            .height(48.dp),
                        onClick = onCancelClick,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = JetDanceTheme.colors.controlColor,
                            disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.no),
                            style = JetDanceTheme.typography.body,
                            color = Color.White
                        )
                    }
                }


            }
        }
    }
}
