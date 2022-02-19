package com.melyseev.seasonticket72.commonview

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.screens.seasonticket.model.OneExercise
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.utils.MAX_SHOW_DAYS_IN_ROW
import com.melyseev.seasonticket72.utils.getTitleForADay

@ExperimentalMaterialApi
@Composable
fun ListExercises(
    exercises: List<OneExercise>,
) {
    var index = 0
    while (index < exercises.size)

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
                ColorCard(
                    modifier = Modifier.size(50.dp),
                    textDateExercise = getTitleForADay(exercises[i].date),
                    onClickedCard = {}
                )
            }
            index = size
        }
}