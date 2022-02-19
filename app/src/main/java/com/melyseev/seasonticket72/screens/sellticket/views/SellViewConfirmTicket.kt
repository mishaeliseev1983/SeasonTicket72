package com.melyseev.seasonticket72.screens.sellticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import com.melyseev.seasonticket72.commonview.ColorCard
import com.melyseev.seasonticket72.commonview.ListExercises
import com.melyseev.seasonticket72.theme.*
import com.melyseev.seasonticket72.utils.getTitleForADay


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SellViewConfirmTicket(
    modifier: Modifier,
    navController: NavController,
    viewState: SellViewState.DisplayConfirmTicket,
    onOk: () -> Unit,
    onCancel: () -> Unit,
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground,

    ) {

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment= Alignment.CenterHorizontally
                ) {
                    /*
                    Text(
                        modifier = Modifier.padding(
                            start = JetDanceTheme.shapes.padding,
                            end = JetDanceTheme.shapes.padding,
                            top = JetDanceTheme.shapes.padding + 8.dp
                        ),
                        text = "Start day: ${viewState.confirmTicketInfo.dateBegin}",
                        style = JetDanceTheme.typography.heading,
                        color = JetDanceTheme.colors.primaryText
                    )
                    */
                    Text(
                        modifier = Modifier
                            .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                                bottom = JetDanceTheme.shapes.padding + 8.dp
                            )
                            .clickable { },
                        text = viewState.confirmTicketInfo.nameSurname,
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
                            .clickable { },
                        text = stringResource(id = R.string.Exercises),
                        style = JetDanceTheme.typography.body,
                        color = JetDanceTheme.colors.controlColor
                    )

                    ListExercises(exercises = viewState.confirmTicketInfo.exercises)
                    /*
                    Row(
                        modifier = Modifier .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                                bottom = JetDanceTheme.shapes.padding + 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        for (i: Int in 0 until viewState.confirmTicketInfo.exercises.size) {
                            ColorCard(

                                color = /*if (isDarkMode) purpleDarkPalette.tintColor else */ baseLightPalette.primaryBackground,
                                textNumberExercise = (i+1).toString(),
                                textDateExercise = getTitleForADay(viewState.confirmTicketInfo.exercises[i].date)
                                )

                        }
                    }

                   // ListExercises(exercises = viewState.confirmTicketInfo.)
*/

                    Row(
                        modifier = Modifier
                            .padding(
                                start = JetDanceTheme.shapes.padding,
                                end = JetDanceTheme.shapes.padding,
                                top = 4.dp,
                                bottom = JetDanceTheme.shapes.padding + 8.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(
                            modifier = Modifier
                                //.padding(top = 16.dp)
                                .padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = 4.dp,
                                )
                                .height(48.dp),
                                //.fillMaxWidth(),
                            onClick = { onOk.invoke() } ,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = JetDanceTheme.colors.controlColor,
                                disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            Text(
                                text =  stringResource(id = R.string.Ok),
                                style = JetDanceTheme.typography.body,
                                color = Color.White
                            )
                        }

                        Button(
                            modifier = Modifier
                                //.padding(top = 16.dp)
                                .padding(
                                    start = JetDanceTheme.shapes.padding,
                                    end = JetDanceTheme.shapes.padding,
                                    top = 4.dp,
                                )
                                .height(48.dp),
                                //.fillMaxWidth(),
                            onClick = { onCancel.invoke() } ,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = JetDanceTheme.colors.controlColor,
                                disabledBackgroundColor = JetDanceTheme.colors.controlColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            Text(
                                text =  stringResource(id = R.string.Cancel),
                                style = JetDanceTheme.typography.body,
                                color = Color.White
                            )
                        }

                    }
                }



    }

}

