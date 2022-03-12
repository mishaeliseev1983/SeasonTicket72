package com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model

sealed class UserInfoViewState {
    data class InfoDisplay(var name: String ="", var surname: String="", val id: Long = -1L, var error: Boolean = false):UserInfoViewState()
    object Loading: UserInfoViewState()
    object SuccessDisplay: UserInfoViewState()
    data class AskDeleteDisplay(val id: Long, var nameSurname: String =""): UserInfoViewState()
    object UserList: UserInfoViewState()
}