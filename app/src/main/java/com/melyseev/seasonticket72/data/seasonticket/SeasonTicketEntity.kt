package com.melyseev.seasonticket72.data.seasonticket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = SeasonTicketEntity.TABLE_SEASON_TICKET)
data class SeasonTicketEntity(

    @PrimaryKey(autoGenerate = true)
        val id: Long = 0L,

    @ColumnInfo(name = ID_USER) val idUser: Long,

    @ColumnInfo(name = DATE_BEGIN) var dateBegin: String,

    @ColumnInfo(name = DATE_END)   var dateEnd: String,

    @ColumnInfo(name = OPEN_STATUS) var openStatus: Boolean = true,

    @ColumnInfo(name = EXERCISES) var exercises: String

    ){
        companion object {
            const val TABLE_SEASON_TICKET = "SeasonTicket_Entity"
            const val ID_USER = "SeasonTicket_IdUser"
            const val DATE_BEGIN = "SeasonTicket_DateBegin"
            const val DATE_END = "SeasonTicket_DateEnd"
            const val OPEN_STATUS = "SeasonTicket_OpenStatus"
            const val EXERCISES = "SeasonTicketExercises"
        }
    }