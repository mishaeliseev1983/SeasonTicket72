package com.melyseev.seasonticket72.screens.sellticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.utils.getTitleForADay


@ExperimentalFoundationApi
@Composable
fun SellViewDisplay(
    modifier: Modifier,
    navController: NavController,
    viewState: SellViewState.Display,
    onNextDayClicked: () -> Unit,
    onPreviousDayClicked: () -> Unit,
    onChangeDays: () -> Unit,
    onShowUsers: () -> Unit,
    onGetTicket: () -> Unit
    ) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground
    ) {
        Box {

            LazyColumn {
                stickyHeader {


                    /*
                    Choose day begin
                     */
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = JetDanceTheme.shapes.padding + 8.dp
                                ),
                                text = getTitleForADay(viewState.fieldTicketInfo.dateBegin),
                                style = JetDanceTheme.typography.heading,
                                color = JetDanceTheme.colors.primaryText
                            )

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
                        }

                        if (viewState.fieldTicketInfo.hasNext) {
                            Icon(
                                modifier = Modifier
                                    .size(56.dp)
                                    .padding(4.dp)
                                    .clickable { onNextDayClicked.invoke() },

                                imageVector = Icons.Filled.ArrowForward,
                                tint = JetDanceTheme.colors.controlColor,
                                contentDescription = stringResource(id = R.string.daily_next_day),
                            )
                        }
                    }


                    /*
                    Choose number days
                     */
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = JetDanceTheme.shapes.padding + 8.dp
                                ),
                                text = "${viewState.fieldTicketInfo.countExercise}",
                                style = JetDanceTheme.typography.heading,
                                color = JetDanceTheme.colors.primaryText
                            )

                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = JetDanceTheme.shapes.padding,
                                        end = JetDanceTheme.shapes.padding,
                                        top = 4.dp,
                                        bottom = JetDanceTheme.shapes.padding + 8.dp
                                    ),
                                text = stringResource(id = R.string.sell_number_days),
                                style = JetDanceTheme.typography.body,
                                color = JetDanceTheme.colors.controlColor
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .size(56.dp)
                                .padding(4.dp)
                                .clickable { onChangeDays.invoke() },

                            imageVector = Icons.Filled.ChangeCircle,
                            tint = JetDanceTheme.colors.controlColor,
                            contentDescription = stringResource(id = R.string.sell_number_days),
                        )
                    }

                    /*
                    Choose user
                     */
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = JetDanceTheme.shapes.padding + 8.dp
                                ),
                                text = viewState.fieldTicketInfo.nameSurname,
                                style = JetDanceTheme.typography.heading,
                                color = JetDanceTheme.colors.primaryText,

                                )

                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = JetDanceTheme.shapes.padding,
                                        end = JetDanceTheme.shapes.padding,
                                        top = 4.dp,
                                        bottom = JetDanceTheme.shapes.padding + 8.dp
                                    ),
                                text = stringResource(id = R.string.select_user),
                                style = JetDanceTheme.typography.body,
                                color = JetDanceTheme.colors.controlColor
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .size(56.dp)
                                .padding(4.dp)
                                .clickable { onShowUsers.invoke() },

                            imageVector = Icons.Filled.SupervisedUserCircle,
                            tint = JetDanceTheme.colors.controlColor,
                            contentDescription = stringResource(id = R.string.select_user),
                        )
                    }

                    /*
                   Get ticket
                     */
                    Button(
                        modifier = Modifier
                            //.padding(top = 16.dp)
                            .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                            )
                            .height(48.dp)
                            .fillMaxWidth(),
                        onClick = { onGetTicket.invoke() } ,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = JetDanceTheme.colors.controlColor,
                            disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Text(
                            text =  stringResource(id = R.string.get_ticket),
                            style = JetDanceTheme.typography.body,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}