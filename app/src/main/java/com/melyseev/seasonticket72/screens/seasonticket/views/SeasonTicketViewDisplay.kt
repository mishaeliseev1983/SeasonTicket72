package com.melyseev.seasonticket72.screens.seasonticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.commonview.ListExercisesSeasonTicket
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.utils.*

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SeasonTicketViewDisplay(
    modifier: Modifier,
    state: SeasonTicketViewState.Display,
    onNextMonthClicked:()->Unit,
    onPreviousMonthClicked:()->Unit,
    onClickedCard: (Long, String)->Unit,
    onShowTicketForClose: (Long)->Unit,
    ) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(8.dp)
                        .clickable { onPreviousMonthClicked.invoke() },

                    imageVector = Icons.Filled.ArrowBack,
                    tint = JetDanceTheme.colors.controlColor,
                    contentDescription = stringResource(id = R.string.previous_month),
                )
                Text(
                    modifier = Modifier.padding(
                        start = JetDanceTheme.shapes.padding,
                        end = JetDanceTheme.shapes.padding,
                        top = JetDanceTheme.shapes.padding + 2.dp
                    ),
                    text = getTitleForADayOnlyMonthYear(state.currentMonth),
                    style = JetDanceTheme.typography.heading,
                    color = JetDanceTheme.colors.primaryText
                )
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(8.dp)
                        .clickable { onNextMonthClicked.invoke() },

                    imageVector = Icons.Filled.ArrowForward,
                    tint = JetDanceTheme.colors.controlColor,
                    contentDescription = stringResource(id = R.string.next_month),
                )
            }

        },
            scaffoldState = scaffoldState){

            Box {
                LazyColumn {
                    val userExercises = state.exercises
                    val users = state.users


                    for (oneUserInfo in userExercises) {
                        val userName = getNameSurname(users = users, id = oneUserInfo.idUser)
                        stickyHeader {
                            ListExercisesSeasonTicket(
                                exercises = oneUserInfo.exercises,
                                onClickedCard =  onClickedCard,
                                idTicketOpenStatus = oneUserInfo.idTicketOpenStatus,
                                onShowTicketForClose = {onShowTicketForClose(oneUserInfo.idTicketOpenStatus)},
                                userName = userName,
                                mapOpenStatus = state.mapOpenStatusTicket
                            )
                        }
                    }
                }
            }

    }
}






