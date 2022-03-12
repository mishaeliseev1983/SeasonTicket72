package com.melyseev.seasonticket72.screens.userlist.screen_userinfo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.commonviews.LoadingView
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model.UserInfoViewEvent
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model.UserInfoViewState
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.view.AskingView
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.view.SuccessView
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.view.UserInfoView

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val viewState = userInfoViewModel.viewState.observeAsState(initial = UserInfoViewState.Loading)
    when(val state = viewState.value) {
        UserInfoViewState.Loading -> {
            println("UserInfoScreen.Loading ...")
            LoadingView()
        }

        UserInfoViewState.SuccessDisplay -> {
            println("UserInfoScreen.SuccessDisplay ...")
            SuccessView(modifier = modifier,
            onCloseClick = {
                navController.navigate("userlist")
            })
        }

        UserInfoViewState.UserList ->{
            navController.navigate("userlist")
        }

        is UserInfoViewState.AskDeleteDisplay -> {
            println("UserInfoScreen.AskDeleteDisplay ...")


            AskingView(modifier = modifier,
                state = state,
                onOkClick = {
                   userInfoViewModel.obtainEvent(
                        UserInfoViewEvent.DeleteClick(
                            id = state.id
                        )
                   )
                },
                onCancelClick = {
                   navController.navigate("userlist")
                })
        }

        is UserInfoViewState.InfoDisplay -> {

            UserInfoView(
                modifier = modifier,
                state = state,
                navController = navController,
                onChangeName = {
                    userInfoViewModel.obtainEvent(UserInfoViewEvent.OnChangeName(it))
                  //  addUserViewModel.obtainEvent(AddUserViewEvent.OnChangeName(it))
                },
                onChangeSurname = {
                    userInfoViewModel.obtainEvent(UserInfoViewEvent.OnChangeSurname(it))
                 //   addUserViewModel.obtainEvent(AddUserViewEvent.OnChangeSurname(it))
                },
                onSaveClicked = { name: String, surname: String, id: Long ->

                    keyboardController?.hide()
                    userInfoViewModel.obtainEvent(
                        UserInfoViewEvent.SaveClick(
                            name = name,
                            surname = surname,
                            id = id
                            )
                    )
                },
                onDeleteClicked = {
                    keyboardController?.hide()
                    userInfoViewModel.obtainEvent(
                        UserInfoViewEvent.DeleteClick(
                            id  = it)
                    )
                },
                onBackUsers = {
                    navController.navigate("userlist")
                }
            )
        }

        /*
        is AddUserViewState.SuccessDisplay -> {
            SuccessAddNewUser(modifier = modifier,
                state = state,
                onCloseClick = {
                    navController.popBackStack()
                })
        }

        AddUserViewState.ErrorDisplay ->{
            ErrorAddNewUser(modifier = modifier, onErrorHappenedClick = {
                addUserViewModel.obtainEvent(AddUserViewEvent.ErrorHappenedClick)})
        }
        */
    }

}