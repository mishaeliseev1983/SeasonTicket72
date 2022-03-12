package com.melyseev.seasonticket72.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_USER)
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = USER_NAME)
    var name: String,

    @ColumnInfo(name = USER_SURNAME)
    var surname: String,
) {
    companion object {
        const val TABLE_USER = "User_Entity"
        const val USER_NAME = "User_Name"
        const val USER_SURNAME = "User_Surname"


    }
}