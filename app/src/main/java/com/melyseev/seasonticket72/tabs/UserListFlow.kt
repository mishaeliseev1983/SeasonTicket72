package com.melyseev.seasonticket72.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.melyseev.seasonticket72.main.MainBottomScreen
import com.melyseev.seasonticket72.screens.adduser.AddUserScreen
import com.melyseev.seasonticket72.screens.adduser.AddUserViewModel
import com.melyseev.seasonticket72.screens.userlist.UserListScreen
import com.melyseev.seasonticket72.screens.userlist.UserListViewModel



@ExperimentalComposeUiApi
@ExperimentalFoundationApi
fun NavGraphBuilder.userListFlow(navController: NavController,
) {
    navigation(startDestination =  "userlist", route = MainBottomScreen.UserList.route) {
        composable( "userlist") {
            val userListViewModel = hiltViewModel<UserListViewModel>()
            UserListScreen(
                navController = navController,
                userListViewModel = userListViewModel
            )
        }

        composable("adduser") {
             val addUserViewModel = hiltViewModel<AddUserViewModel>()
             AddUserScreen(navController = navController, addUserViewModel = addUserViewModel)
        }
    }
}