package com.melyseev.seasonticket72.screens.seasonticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.commonview.ListExercisesSeasonTicketForClose
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SeasonTicketViewTicketCloseOpen(
    modifier: Modifier,
    state: SeasonTicketViewState.DisplayTicketCloseOpen,
    onSelectedCard: (Int)-> Unit,
    onCloseSeasonTicket: ()->Unit,
    onOpenSeasonTicket: ()->Unit,
    onBackToSell:()->Unit,
) {

    val ticketsExercises = state.listExercises

    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground
    ) {
        Box {
            LazyColumn {

                stickyHeader {

                    Text(
                        modifier = Modifier.padding(
                            start = JetDanceTheme.shapes.padding,
                            end = JetDanceTheme.shapes.padding,
                            top = JetDanceTheme.shapes.padding + 8.dp
                        ),
                        text =  stringResource(id = R.string.OpenCloseTicket),
                        style = JetDanceTheme.typography.heading,
                        color = JetDanceTheme.colors.primaryText
                    )

                    ListExercisesSeasonTicketForClose(
                        ticketsExercises = ticketsExercises,
                        openStatus = state.openStatus,
                        //onSelectedCard = {
                        //    onSelectedCard(it)
                        //},
                        userName = state.nameSurname,
                    )

                    Row(
                        modifier = Modifier
                            .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                                bottom = JetDanceTheme.shapes.padding + 8.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                //.padding(top = 16.dp)
                                .padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = 30.dp,
                                )
                                .height(48.dp),
                            onClick = {
                                onCloseSeasonTicket()
                            },

                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = JetDanceTheme.colors.controlColor,
                                disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.Close),
                                style = JetDanceTheme.typography.body,
                                color = Color.White
                            )
                        }



                    }


                    Row(
                        modifier = Modifier
                            .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                                bottom = JetDanceTheme.shapes.padding + 8.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                //.padding(top = 16.dp)
                                .padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = 30.dp,
                                )
                                .height(48.dp),
                            onClick = {
                                onOpenSeasonTicket()
                            },

                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = JetDanceTheme.colors.controlColor,
                                disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.Open),
                                style = JetDanceTheme.typography.body,
                                color = Color.White
                            )
                        }
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
                    onBackToSell()
                }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back to tickets",
                    tint = Color.White
                )
            }
        }
        }
    }