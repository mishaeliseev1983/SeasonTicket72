package com.melyseev.seasonticket72.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketDao
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketEntity
import com.melyseev.seasonticket72.data.user.UserDao
import com.melyseev.seasonticket72.data.user.UserEntity

@Database(entities = [UserEntity::class, SeasonTicketEntity::class], version = 14, exportSchema = false)
abstract class DanceDatabase  : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun seasonTicketDao(): SeasonTicketDao
}