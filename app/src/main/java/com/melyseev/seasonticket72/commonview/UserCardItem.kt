package com.melyseev.seasonticket72.commonview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.theme.JetDanceTheme


data class UserCardItemModel(
    val id: Long,
    var name: String,
    val surname: String,
    var isSelected: Boolean = false
)


@Composable
fun UserCardItem(
    model: UserCardItemModel,
    onClickCardItem:()->Unit,
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = JetDanceTheme.shapes.padding,
                vertical = JetDanceTheme.shapes.padding / 2
            )
            .fillMaxWidth()
            .clickable {
                 onClickCardItem()
            },


        elevation = 8.dp,
        backgroundColor = if (model.isSelected) JetDanceTheme.colors.controlColor else JetDanceTheme.colors.primaryBackground ,
        shape = JetDanceTheme.shapes.cornersStyle
    ) {
        Row(
            modifier = Modifier
                .padding(JetDanceTheme.shapes.padding)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = model.name,
                style = JetDanceTheme.typography.body,
                color = JetDanceTheme.colors.primaryText
            )
/*
            Checkbox(
                checked = model.isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = JetDanceTheme.colors.tintColor,
                    uncheckedColor = JetDanceTheme.colors.secondaryText
                )
            )

 */
        }
    }
}