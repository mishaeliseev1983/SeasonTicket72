package com.melyseev.seasonticket72.screens.userlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.commonviews.LoadingView
import com.melyseev.seasonticket72.screens.userlist.model.UserListViewEvent

import com.melyseev.seasonticket72.screens.userlist.model.UserListViewState
import com.melyseev.seasonticket72.screens.userlist.views.UserListDisplay

@ExperimentalFoundationApi
@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userListViewModel: UserListViewModel,
) {

    val viewState = userListViewModel.userListViewState.observeAsState()

    when(val state = viewState.value){
        UserListViewState.Loading -> LoadingView()

        UserListViewState.NoItems -> LoadingView()

        UserListViewState.Error -> LoadingView()

        is UserListViewState.Display -> UserListDisplay(

            modifier = modifier,
            navController = navController,
            viewState = state
        )

    }

    LaunchedEffect(key1 = viewState, block = {
        userListViewModel.obtainEvent(event = UserListViewEvent.EnterScreen)
    })


}