package com.melyseev.seasonticket72.screens.userlist.trash

import com.melyseev.seasonticket72.commonview.UserCardItemModel


sealed class UserListChooseViewState {
    object Loading: UserListChooseViewState()
    object NoItems: UserListChooseViewState()
    object Error: UserListChooseViewState()
    data class Display(val items: MutableList<UserCardItemModel>, val selected: Long = 0L): UserListChooseViewState()
}