package com.melyseev.seasonticket72.screens.userlist.trash


sealed class UserListChooseViewEvent {
    object EnterScreen: UserListChooseViewEvent()
    data class ChooseUser(val selectedId: Long): UserListChooseViewEvent()
}