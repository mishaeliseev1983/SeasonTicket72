package com.melyseev.seasonticket72.commonview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.screens.seasonticket.model.OneExercise
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.theme.baseLightPalette
import com.melyseev.seasonticket72.utils.MAX_SHOW_DAYS_IN_ROW
import com.melyseev.seasonticket72.utils.getTitleDayOnlyDay
import com.melyseev.seasonticket72.utils.getTitleForADay
import java.util.*


@ExperimentalMaterialApi
@Composable
fun ListExercisesSeasonTicketForClose(
    ticketsExercises: List<OneExercise>,
    //onSelectedCard : (Int)-> Unit,
    userName: String,
    openStatus: Boolean = false
) {

    Text(modifier = Modifier
        .padding(
            start = JetDanceTheme.shapes.padding,
            end = JetDanceTheme.shapes.padding,
            top = 8.dp,
            bottom = JetDanceTheme.shapes.padding + 8.dp
        )


        .fillMaxWidth(),
        text = userName,
        style = JetDanceTheme.typography.body,
        color = JetDanceTheme.colors.primaryText)

    var index = 0
    while (index < ticketsExercises.size)

        Row(
            modifier = Modifier
                .padding(
                    start = JetDanceTheme.shapes.padding,
                    end = JetDanceTheme.shapes.padding,
                    top = 4.dp,
                    bottom = JetDanceTheme.shapes.padding + 8.dp
                )

                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            var size = index + MAX_SHOW_DAYS_IN_ROW
            if (size > ticketsExercises.size) size = ticketsExercises.size



            for (i in index until size) {

                ColorCard(
                    modifier = Modifier.size(70.dp),
                    textDateExercise = getTitleForADay(ticketsExercises[i].date),
                    onClickedCard = {},
                    openStatus = openStatus,
                    status = ticketsExercises[i].status
                )
            }
            index = size
        }
}