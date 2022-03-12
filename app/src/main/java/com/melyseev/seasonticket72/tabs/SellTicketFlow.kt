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
import com.melyseev.seasonticket72.screens.sellticket.SellScreen
import com.melyseev.seasonticket72.screens.sellticket.SellViewModel


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
fun NavGraphBuilder.sellTicketFlow(navController: NavController,
) {
    navigation(startDestination = "sellticket", route = MainBottomScreen.SellTicket.route) {
        composable("sellticket") {
            val sellViewModel = hiltViewModel<SellViewModel>()
            SellScreen(
                navController = navController,
                sellViewModel = sellViewModel
            )
        }

    }
}
