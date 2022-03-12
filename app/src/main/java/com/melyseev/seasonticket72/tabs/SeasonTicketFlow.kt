package com.melyseev.seasonticket72.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.melyseev.seasonticket72.main.MainBottomScreen
import com.melyseev.seasonticket72.screens.seasonticket.SeasonTicketScreen
import com.melyseev.seasonticket72.screens.seasonticket.SeasonTicketViewModel
import com.melyseev.seasonticket72.screens.userlist.UserListScreen
import com.melyseev.seasonticket72.screens.userlist.UserListViewModel


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
fun NavGraphBuilder.seasonTicketFlow(navController: NavController,
) {
    navigation(startDestination = "seasonticket", route = MainBottomScreen.SeasonTicket.route) {
        composable("seasonticket") {
            val seasonTicketViewModel = hiltViewModel<SeasonTicketViewModel>()
            SeasonTicketScreen(
                navController = navController,
                seasonTicketViewModel = seasonTicketViewModel
            )
        }
    }
}