package com.melyseev.seasonticket72.commonview


import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.theme.*
import com.melyseev.seasonticket72.utils.getBackgroundColorCard
import com.melyseev.seasonticket72.utils.getColorCircle


@ExperimentalMaterialApi
@Composable
fun ColorCard(
    modifier: Modifier,
    textDateExercise: String,
    onClickedCard: ()-> Unit,
    openStatus: Boolean? = null,
    status: Int = 0
    ) {

    //var color = baseLightPalette.secondaryBackground
    //if(!openStatus)
     //   color = Color.LightGray // baseLightPalette.controlColor

    var icon = Icons.Filled.Circle


    val colorCircle = getColorCircle(status = status, openStatus = openStatus)
    var colorBackground = getBackgroundColorCard(openStatus = openStatus)
    Card(
        modifier = modifier,
        backgroundColor = colorBackground,
        elevation = 15.dp,
        shape = JetDanceTheme.shapes.cornersStyle,
        onClick = { onClickedCard.invoke()}
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = textDateExercise)
            }


            openStatus?.let{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = icon, // Icons.Filled.Close,
                            contentDescription = null,
                            tint = colorCircle
                        )
                    }
            }
        }

    }
}