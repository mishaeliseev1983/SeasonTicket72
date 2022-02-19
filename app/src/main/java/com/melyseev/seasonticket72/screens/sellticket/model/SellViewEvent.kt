package com.melyseev.seasonticket72.screens.sellticket.model

import com.melyseev.seasonticket72.data.user.UserEntity

sealed class SellViewEvent {
    object SaveEnterScreen : SellViewEvent()
    object EnterScreen : SellViewEvent()
    object NextDayClicked : SellViewEvent()
    object PreviousDayClicked : SellViewEvent()
    object ChangeDaysNumberClicked : SellViewEvent()
    object ShowUsers : SellViewEvent()
    object GetTicket : SellViewEvent()
    data class SelectUser(val selectedUser: UserEntity?) : SellViewEvent()
}
