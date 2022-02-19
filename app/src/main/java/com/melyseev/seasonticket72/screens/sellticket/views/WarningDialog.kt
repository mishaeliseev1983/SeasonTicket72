package com.melyseev.seasonticket72.screens.sellticket.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.screens.sellticket.model.TicketInfo

@Composable
fun WarningDialog(
    ticketInfo: TicketInfo,
    showWarnings: MutableState<String>,
) {

    if(showWarnings.value.isEmpty()) return
    AlertDialog(
        modifier = Modifier
            //.fillMaxSize()
            .padding(8.dp)
        //.testTag(TAG_HERO_FILTER_DIALOG)
        ,
        onDismissRequest = {
            ticketInfo.warning = ""
            showWarnings.value = ""
        },
        text = {
            Text(
                text = ticketInfo.warning  ,
                style = MaterialTheme.typography.h4,
            )
        },
        buttons = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    // make the icon larger so it's easier to click
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            ticketInfo.warning = ""
                            showWarnings.value = ""
                        },
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp),
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = Color(0xFF009a34)
                    )
                }
            }
        })


}