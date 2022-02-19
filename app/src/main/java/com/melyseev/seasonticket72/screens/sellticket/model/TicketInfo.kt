package com.melyseev.seasonticket72.screens.sellticket.model

import com.melyseev.seasonticket72.screens.seasonticket.model.OneExercise
import java.util.*

class TicketInfo(
    var nameSurname: String = "?",
    var dateBegin: Date = Date(),
    var countExercise: Int = 2,
    var idUserForSale: Long = -1L,
    var hasNext: Boolean = false,
    var warning: String = "",
    val exercises: MutableList<OneExercise> = mutableListOf()
)