package com.melyseev.seasonticket72.screens.userlist.model

import com.melyseev.seasonticket72.commonview.UserCardItemModel


sealed class UserListViewState {
    object Loading: UserListViewState()
    object NoItems: UserListViewState()
    object Error: UserListViewState()
    data class Display(val items: List<UserCardItemModel>): UserListViewState()
}