package com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model

sealed class UserInfoViewEvent {
    data class SaveClick(val id: Long, val name: String, val surname: String): UserInfoViewEvent()
    data class DeleteClick(val id: Long): UserInfoViewEvent()
    object ErrorHappenedClick: UserInfoViewEvent()
    data class OnChangeName(val name: String): UserInfoViewEvent()
    data class OnChangeSurname(val surname: String): UserInfoViewEvent()
}