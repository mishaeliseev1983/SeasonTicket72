package com.melyseev.seasonticket72.screens.adduser

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.melyseev.seasonticket72.screens.adduser.model.AddUserViewEvent
import com.melyseev.seasonticket72.screens.adduser.model.AddUserViewState
import com.melyseev.seasonticket72.screens.adduser.views.ErrorAddNewUser
import com.melyseev.seasonticket72.screens.adduser.views.InitialAddNewUser
import com.melyseev.seasonticket72.screens.adduser.views.SuccessAddNewUser



@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AddUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    addUserViewModel: AddUserViewModel
) {

    val viewState = addUserViewModel.viewState.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    when(val state = viewState.value) {
        is AddUserViewState.AddDisplay -> {
            InitialAddNewUser(
                modifier = modifier,
                state = state,
                navController = navController,
                onChangeName = {
                    addUserViewModel.obtainEvent(AddUserViewEvent.OnChangeName(it))
                },
                onChangeSurname = {
                    addUserViewModel.obtainEvent(AddUserViewEvent.OnChangeSurname(it))
                },
                onSaveClicked = {
                    keyboardController?.hide()
                    addUserViewModel.obtainEvent(AddUserViewEvent.SaveClick)
                },
            )
        }

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
    }

}