package com.melyseev.seasonticket72.screens.sellticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.commonviews.LoadingView
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewEvent
import com.melyseev.seasonticket72.screens.sellticket.model.SellViewState
import com.melyseev.seasonticket72.screens.sellticket.views.SellViewConfirmTicket
import com.melyseev.seasonticket72.screens.sellticket.views.SellViewDisplay
import com.melyseev.seasonticket72.screens.sellticket.views.SellViewMessage
import com.melyseev.seasonticket72.screens.sellticket.views.SellViewUsers
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SellScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    sellViewModel: SellViewModel) {

    val viewState = sellViewModel.sellViewState.observeAsState(initial = SellViewState.Loading)
    when (val state = viewState.value) {

        SellViewState.Loading -> {
            println("SellScreen.Loading ...")
            LoadingView()
        }

        is SellViewState.DisplayConfirmTicket -> {
            SellViewConfirmTicket(
                modifier = modifier,
                navController = navController,
                viewState = state,
                onOk = { sellViewModel.obtainEvent(SellViewEvent.SaveEnterScreen) },
                onCancel = { sellViewModel.obtainEvent(SellViewEvent.EnterScreen) })
            }


        is SellViewState.DisplayMessage -> {
            SellViewMessage(
                state = state,
                onCloseClick = { sellViewModel.obtainEvent(SellViewEvent.EnterScreen) }
            )
        }
        is SellViewState.Display -> {
            SellViewDisplay(
                modifier = modifier,
                navController = navController,
                viewState = state,
                onNextDayClicked = { sellViewModel.obtainEvent(SellViewEvent.NextDayClicked) },
                onPreviousDayClicked = { sellViewModel.obtainEvent(SellViewEvent.PreviousDayClicked) },
                onChangeDays = { sellViewModel.obtainEvent(SellViewEvent.ChangeDaysNumberClicked)},
                onShowUsers = { sellViewModel.obtainEvent(SellViewEvent.ShowUsers) },
                onGetTicket = { sellViewModel.obtainEvent(SellViewEvent.GetTicket) }
            )
        }

        is SellViewState.DisplayUsers -> {
            SellViewUsers(
                modifier = modifier,
                navController = navController,
                viewState = state,
                onBackToSell = {
                    sellViewModel.obtainEvent(SellViewEvent.EnterScreen)
                },

                onClickCardItem = {
                    sellViewModel.viewModelScope.launch {
                        val user =   sellViewModel.userRepository.getUserNameSurnameById( it )
                        sellViewModel.selectedUser = user
                        sellViewModel.obtainEvent(SellViewEvent.SelectUser(user))
                    }
                }
                )
        }
    }


    LaunchedEffect(key1 = "viewState", block = {
        //if(sellViewModel.userRepository.selectedUserId!=null)
        //    sellViewModel.obtainEvent(SellViewEvent.EnterScreenShowUser)
        //else
            sellViewModel.obtainEvent(SellViewEvent.EnterScreen)
    })
}