package com.melyseev.seasonticket72.screens.seasonticket.model


sealed class SeasonTicketViewEvent {
    object EnterScreen : SeasonTicketViewEvent()

    object CloseSeasonTicket: SeasonTicketViewEvent()
    object OpenSeasonTicket: SeasonTicketViewEvent()
    object NextMonthClicked : SeasonTicketViewEvent()
    object PreviousMonthClicked : SeasonTicketViewEvent()
    data class EditTicket(val idTicket: Long, val date: String) : SeasonTicketViewEvent()
    data class SelectedCard(val selectedIndex: Int): SeasonTicketViewEvent()
    data class ShowTicketForClose(val idTicket: Long): SeasonTicketViewEvent()
}
