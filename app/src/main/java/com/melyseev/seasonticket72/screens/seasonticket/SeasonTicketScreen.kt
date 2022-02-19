package com.melyseev.seasonticket72.screens.seasonticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewEvent
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewState
import com.melyseev.seasonticket72.screens.seasonticket.views.SeasonTicketViewDisplay
import com.melyseev.seasonticket72.screens.seasonticket.views.SeasonTicketViewTicketCloseOpen
import com.melyseev.seasonticket72.screens.seasonticket.views.SeasonTicketViewLoading
import com.melyseev.seasonticket72.screens.seasonticket.views.SeasonTicketViewUserGuide

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SeasonTicketScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    seasonTicketViewModel: SeasonTicketViewModel) {


    val viewState = seasonTicketViewModel.seasonTicketViewState.observeAsState()

    when (val state = viewState.value) {
        SeasonTicketViewState.Loading ->SeasonTicketViewLoading()
        SeasonTicketViewState.DisplayUserGuide ->SeasonTicketViewUserGuide()
        is SeasonTicketViewState.Display ->SeasonTicketViewDisplay(
            modifier,
            state,

            onNextMonthClicked = {
                seasonTicketViewModel.obtainEvent(event = SeasonTicketViewEvent.NextMonthClicked)
            },
            onPreviousMonthClicked = {
                seasonTicketViewModel.obtainEvent(event = SeasonTicketViewEvent.PreviousMonthClicked)
            },
            onClickedCard = {

                idTicket: Long,  date: String, ->
                seasonTicketViewModel.obtainEvent(event = SeasonTicketViewEvent.EditTicket(idTicket = idTicket, date = date))
            },
            onShowTicketForClose = {

                seasonTicketViewModel.obtainEvent(event = SeasonTicketViewEvent.ShowTicketForClose(idTicket = it))
            }
        )

        is SeasonTicketViewState.DisplayTicketCloseOpen -> SeasonTicketViewTicketCloseOpen(
            modifier,
            state,
            onSelectedCard = {
            },
            onCloseSeasonTicket = {
                seasonTicketViewModel.obtainEvent(SeasonTicketViewEvent.CloseSeasonTicket)
            },
            onOpenSeasonTicket = {
                seasonTicketViewModel.obtainEvent(SeasonTicketViewEvent.OpenSeasonTicket)
            },
            onBackToSell = {

                seasonTicketViewModel.obtainEvent(SeasonTicketViewEvent.EnterScreen)
            },

            )
    }
    LaunchedEffect(key1 = "viewState", block = {
        //if(sellViewModel.userRepository.selectedUserId!=null)
        //    sellViewModel.obtainEvent(SellViewEvent.EnterScreenShowUser)
        //else
        seasonTicketViewModel.obtainEvent(SeasonTicketViewEvent.EnterScreen)
    })
}

