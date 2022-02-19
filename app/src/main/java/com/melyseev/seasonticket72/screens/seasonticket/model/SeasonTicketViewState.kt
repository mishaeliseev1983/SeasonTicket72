package com.melyseev.seasonticket72.screens.seasonticket.model

import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketEntity
import com.melyseev.seasonticket72.data.user.UserEntity
import java.util.*


sealed class SeasonTicketViewState {
    object Loading: SeasonTicketViewState()
    object DisplayUserGuide: SeasonTicketViewState()
    data class Display(
        var currentMonth:Date = Date(),
        val exercises: List<UserExercises>,
        val users: List<UserEntity>,
        val mapOpenStatusTicket: MutableMap<Long, Boolean>): SeasonTicketViewState()
    data class DisplayTicketCloseOpen(
        val id: Long,
        val nameSurname: String,
        val currentMonth: Date,
        val listExercises:  List<OneExercise>,
        val openStatus: Boolean): SeasonTicketViewState()
}