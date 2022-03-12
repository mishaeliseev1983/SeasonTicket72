package com.melyseev.seasonticket72.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.melyseev.seasonticket72.main.MainBottomScreen
import com.melyseev.seasonticket72.screens.userlist.screen_adduser.AddUserScreen
import com.melyseev.seasonticket72.screens.userlist.screen_adduser.AddUserViewModel
import com.melyseev.seasonticket72.screens.userlist.UserListScreen
import com.melyseev.seasonticket72.screens.userlist.UserListViewModel
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.UserInfoScreen
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.UserInfoViewModel
import com.melyseev.seasonticket72.utils.ARGUMENT_KEY_USERID


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

        composable("infouser/".plus("?$ARGUMENT_KEY_USERID={$ARGUMENT_KEY_USERID}"),
            arguments = listOf(navArgument(ARGUMENT_KEY_USERID) { type = NavType.LongType })) {
            val userInfoViewModel = hiltViewModel<UserInfoViewModel>()
            val userId = it.arguments?.getLong(ARGUMENT_KEY_USERID)?:-1L
            UserInfoScreen(navController = navController, userInfoViewModel = userInfoViewModel)
            userInfoViewModel.postNewState(userId)
        }
    }
}