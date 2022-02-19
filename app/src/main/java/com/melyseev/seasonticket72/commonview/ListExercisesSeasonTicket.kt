package com.melyseev.seasonticket72.commonview

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.screens.seasonticket.model.OneExercise
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.utils.MAX_SHOW_DAYS_IN_ROW
import com.melyseev.seasonticket72.utils.getTitleDayOnlyDay

@ExperimentalMaterialApi
@Composable
fun ListExercisesSeasonTicket(
    exercises: List<OneExercise>,
    onClickedCard: (Long, String)->Unit,
    onShowTicketForClose: ()->Unit,
    userName: String,
    idTicketOpenStatus: Long = -1L,
    mapOpenStatus: MutableMap<Long, Boolean> = mutableMapOf()
    ) {

    Row(
        modifier = Modifier
            .padding(
                start = JetDanceTheme.shapes.padding,
                end = JetDanceTheme.shapes.padding,
                top = 4.dp,
                bottom = JetDanceTheme.shapes.padding + 8.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = userName,
            style = JetDanceTheme.typography.body,
            color = JetDanceTheme.colors.controlColor
        )


        if(idTicketOpenStatus!=-1L)
        IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
            onClick = {
                onShowTicketForClose()
            }) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = "Edit ticket",
                tint = JetDanceTheme.colors.tintColor
            )
        }

    }

    var index = 0
    while (index < exercises.size)
    {
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
            if (size > exercises.size) size = exercises.size

            for (i in index until size) {

                val exercise= exercises[i]

                ColorCard(
                    modifier = Modifier.size(50.dp),
                    status = exercise.status,
                    openStatus =  mapOpenStatus[exercise.idTicket],
                    textDateExercise = getTitleDayOnlyDay(exercises[i].date),
                    onClickedCard = {
                        onClickedCard( exercise.idTicket,  exercise.date )}
                )
            }
            index = size
        }
    }

    Divider(color = JetDanceTheme.colors.tintColor, thickness = 1.dp)
}
