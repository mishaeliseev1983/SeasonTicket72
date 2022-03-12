package com.melyseev.seasonticket72.screens.userlist.model

sealed class UserListViewEvent {
    object EnterScreen: UserListViewEvent()
    object AddUser: UserListViewEvent()

    /*
    object OnSaveNewUser: UserListViewEvent()
    data class OnChangeName(val name: String): UserListViewEvent()
    data class OnChangeSurname(val surname: String): UserListViewEvent()
    */

}