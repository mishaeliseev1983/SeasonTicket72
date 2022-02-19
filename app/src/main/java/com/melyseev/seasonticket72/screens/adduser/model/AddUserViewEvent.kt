package com.melyseev.seasonticket72.screens.adduser.model

sealed class AddUserViewEvent {
    object SaveClick: AddUserViewEvent()
    object ErrorHappenedClick: AddUserViewEvent()
    data class OnChangeName(val name: String): AddUserViewEvent()
    data class OnChangeSurname(val surname: String): AddUserViewEvent()
}