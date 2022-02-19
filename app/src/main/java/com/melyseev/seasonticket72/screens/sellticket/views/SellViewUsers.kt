package com.melyseev.seasonticket72.screens.sellticket.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewState
import com.melyseev.seasonticket72.theme.JetDanceTheme
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.commonview.UserCardItem
import com.melyseev.seasonticket72.data.user.UserEntity

@ExperimentalFoundationApi
    @Composable
    fun SellViewUsers(
        modifier: Modifier = Modifier,
        navController: NavController,
        viewState: SellViewState.DisplayUsers,
        onBackToSell:()->Unit,
        onClickCardItem:(Long)->Unit
    ) {

        Surface(
            modifier = modifier.fillMaxSize(),
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
                                    text =  stringResource(id = R.string.user_list),
                                    style = JetDanceTheme.typography.heading,
                                    color = JetDanceTheme.colors.primaryText
                                )
                            }
                        }
                    }
                    viewState.items.forEach{
                        //it.isSelected  = false
                        item{
                            UserCardItem(model = it,
                                onClickCardItem = {
                                            onClickCardItem(it.id)
                                            //it.isSelected = true
                                }
                            )
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
                        contentDescription = "Back to sell",
                        tint = Color.White
                    )
                }

            }
        }
    }