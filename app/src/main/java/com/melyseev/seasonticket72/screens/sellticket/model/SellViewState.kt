package com.melyseev.seasonticket72.screens.sellticket.model

import com.melyseev.seasonticket72.commonview.UserCardItemModel


sealed class SellViewState {
    object Loading: SellViewState()

    data class Display(val fieldTicketInfo: TicketInfo): SellViewState()

    data class DisplayUsers( val items: List<UserCardItemModel>, val ticketInfo: TicketInfo): SellViewState()

    data class DisplayConfirmTicket(var confirmTicketInfo: TicketInfo): SellViewState()

    data class DisplayMessage(val message: String, val success: Boolean = true): SellViewState()
}