package com.melyseev.seasonticket72.screens.userlist.screen_adduser.model

sealed class AddUserViewState {
    data class AddDisplay(val name: String, val surname : String, var error: Boolean = false): AddUserViewState()
    data class SuccessDisplay(val new_name: String): AddUserViewState()
    object ErrorDisplay: AddUserViewState()
}