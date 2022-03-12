package com.melyseev.seasonticket72.screens.userlist.screen_adduser.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.userlist.screen_adduser.model.AddUserViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme

@ExperimentalFoundationApi
@Composable
fun InitialAddNewUser(
    modifier: Modifier,
    state: AddUserViewState.AddDisplay,
    navController: NavController,
    onChangeName: (String) -> Unit,
    onChangeSurname: (String) -> Unit,
    onSaveClicked: () -> Unit, ) {

    /*
    if(state.error) {
        ErrorAddNewUser(
            modifier = modifier,
            onErrorHappenedClick = onErrorHappenedClick
        )
    }else*/
    Surface(modifier = modifier.fillMaxSize(), color = JetDanceTheme.colors.primaryBackground) {
        Box {
            LazyColumn(
                Modifier.background(JetDanceTheme.colors.primaryBackground),
                content = {
                    stickyHeader {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = JetDanceTheme.shapes.padding,
                                vertical = JetDanceTheme.shapes.padding + 8.dp
                            ),
                            text = stringResource(id = R.string.add_user_new_record),
                            style = JetDanceTheme.typography.heading,
                            color = JetDanceTheme.colors.primaryText
                        )
                    }
                    item {
                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            Text(
                                text = stringResource(id = R.string.add_user_name),
                                style = JetDanceTheme.typography.caption,
                                color = JetDanceTheme.colors.secondaryText
                            )

                            TextField(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .fillMaxWidth(),
                                singleLine = true,
                                enabled = true, //!state.isSending,
                                value = state.name, //state.habitTitle,
                                onValueChange = onChangeName,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = JetDanceTheme.colors.primaryBackground,
                                    textColor = JetDanceTheme.colors.primaryText,
                                    focusedIndicatorColor = JetDanceTheme.colors.tintColor,
                                    disabledIndicatorColor = JetDanceTheme.colors.controlColor,
                                    cursorColor = JetDanceTheme.colors.tintColor
                                )
                            )

                            Text(
                                text = stringResource(id = R.string.add_user_surname),
                                style = JetDanceTheme.typography.caption,
                                color = JetDanceTheme.colors.secondaryText
                            )

                            TextField(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .fillMaxWidth(),
                                singleLine = true,
                                enabled = true, //!state.isSending,
                                value = state.surname, //state.habitTitle,
                                onValueChange = onChangeSurname,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = JetDanceTheme.colors.primaryBackground,
                                    textColor = JetDanceTheme.colors.primaryText,
                                    focusedIndicatorColor = JetDanceTheme.colors.tintColor,
                                    disabledIndicatorColor = JetDanceTheme.colors.controlColor,
                                    cursorColor = JetDanceTheme.colors.tintColor
                                )
                            )

                        }
                    }

                    item {
                        Button(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .height(48.dp)
                                .fillMaxWidth(),
                            onClick = onSaveClicked,
                            enabled = true,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = JetDanceTheme.colors.tintColor,
                                disabledBackgroundColor = JetDanceTheme.colors.tintColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            /*
                            if (state.isSending) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {*/
                                Text(
                                    text = stringResource(id = R.string.action_add),
                                    style = JetDanceTheme.typography.body,
                                    color = Color.White
                                )
                           // }
                        }
                    }



                })
        }
    }
}