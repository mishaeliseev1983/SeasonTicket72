package com.melyseev.seasonticket72.screens.userlistchoose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.commonviews.LoadingView
import com.melyseev.seasonticket72.screens.userlist.trash.UserListChooseViewModel
import com.melyseev.seasonticket72.screens.userlist.trash.UserListChooseViewEvent

import com.melyseev.seasonticket72.screens.userlist.trash.UserListChooseViewState
import com.melyseev.seasonticket72.screens.userlist.views.UserListChooseDisplay


@ExperimentalFoundationApi
@Composable
fun UserListChooseScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userListChooseViewModel: UserListChooseViewModel,
) {

    val viewState = userListChooseViewModel.userListViewState.observeAsState()

    when(val state = viewState.value){
        UserListChooseViewState.Loading -> LoadingView()

        UserListChooseViewState.NoItems -> LoadingView()

        UserListChooseViewState.Error -> LoadingView()

        is UserListChooseViewState.Display -> UserListChooseDisplay(
            modifier = modifier,
            navController = navController,
            viewState = state,
            onUpdateSelectedItem =  {
                    userListChooseViewModel.obtainEvent(UserListChooseViewEvent.ChooseUser(it.id))
                },
            onBackToSell = {

               navController.navigate("sellticket/${state.selected}")

               /*{
                   popUpTo("sellticketUserlistChoose") { inclusive = true }
               }*/
            }
        )

    }



    LaunchedEffect(key1 = viewState, block = {
        userListChooseViewModel.obtainEvent(event = UserListChooseViewEvent.EnterScreen)
    })


}