package com.melyseev.seasonticket72.screens.seasonticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.screens.commonviews.LoadingView
import com.melyseev.seasonticket72.theme.JetDanceTheme

@ExperimentalFoundationApi
@Composable
fun SeasonTicketViewUserGuide() {



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = JetDanceTheme.colors.primaryBackground
    ) {
        Box {

            LazyColumn {
                stickyHeader {
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
                                text = stringResource(id = R.string.userguide),
                                style = JetDanceTheme.typography.body,
                                color = JetDanceTheme.colors.primaryText
                            )
                        }
                    }
                }
            }
        }
    }
}