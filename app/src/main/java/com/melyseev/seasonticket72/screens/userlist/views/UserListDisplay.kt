package com.melyseev.seasonticket72.screens.userlist.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.userlist.model.UserListViewState
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.commonview.UserCardItem
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.utils.ARGUMENT_KEY_USERID


@ExperimentalFoundationApi
@Composable
fun UserListDisplay(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewState: UserListViewState.Display,
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground
    ) {
    Box {

        LazyColumn {
            stickyHeader {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            modifier = Modifier.padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = JetDanceTheme.shapes.padding + 8.dp
                            ),
                            text =  stringResource(id = R.string.user_list),
                            style = JetDanceTheme.typography.heading,
                            color = JetDanceTheme.colors.primaryText
                        )
                    }
                }
            }
/*
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = 4.dp,
                                    bottom = JetDanceTheme.shapes.padding + 8.dp
                                )
                                .clickable { onPreviousDayClicked.invoke() },
                            text = stringResource(id = R.string.daily_previous_day),
                            style = JetDanceTheme.typography.body,
                            color = JetDanceTheme.colors.controlColor
                        )

 */

/*
                    if (viewState.hasNextDay) {
                        Icon(
                            modifier = Modifier
                                .size(56.dp)
                                .padding(16.dp)
                                .clickable { onNextDayClicked.invoke() },
                            imageVector = Icons.Filled.ArrowForward,
                            tint = JetDanceTheme.colors.controlColor,
                            contentDescription = "Next Day"
                        )
                    }
                }
            }

 */



                    viewState.items.forEach{
                        item{
                            UserCardItem(model = it,

                                onClickCardItem = {
                                    //second way
                                    //navController.navigate("infouser/{${it.id}}")

                                    navController.navigate("infouser/?$ARGUMENT_KEY_USERID=${it.id}")

                            })
                        }
                    }

        }
        //for userlist flow
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(JetDanceTheme.shapes.padding),
            backgroundColor = JetDanceTheme.colors.tintColor,
            onClick = {

                // addUserEvent()

                //second way
                navController.navigate("adduser")
            }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Settings icon",
                tint = Color.White
            )
        }

    }
    }
}