package com.melyseev.seasonticket72.data.seasonticket

import androidx.room.*
import com.melyseev.seasonticket72.data.user.UserEntity
import java.util.*


@Dao
interface SeasonTicketDao {

    @Insert
    suspend fun insert(entity: SeasonTicketEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: SeasonTicketEntity)

    @Query("SELECT * from ${SeasonTicketEntity.TABLE_SEASON_TICKET}")
    suspend fun getAll(): List<SeasonTicketEntity>


    /*
    MONTH - substr(SeasonTicket_DateBegin, 4, 2)
    YEAR -  substr(SeasonTicket_DateBegin, 7, 4)
     */
    @Query("SELECT *  from ${SeasonTicketEntity.TABLE_SEASON_TICKET}  WHERE ((CAST(substr(SeasonTicket_DateBegin, 7, 4) as INT) == :YEAR AND CAST(substr(SeasonTicket_DateBegin, 4, 2) as INT) == :MONTH) OR (CAST(substr(SeasonTicket_DateEnd, 7, 4) as INT) == :YEAR AND CAST(substr(SeasonTicket_DateEnd, 4, 2) as INT) == :MONTH)) AND SeasonTicket_IdUser = :idUser ORDER BY SeasonTicket_DateEnd")
    suspend fun fetchSeasonTicketsByUserByDate(MONTH: Int, YEAR: Int, idUser: Long): List<SeasonTicketEntity>




    /*
    MONTH - substr(SeasonTicket_DateBegin, 4, 2)
    YEAR -  substr(SeasonTicket_DateBegin, 7, 4)
     */
    /*
    @Query("SELECT *  from ${SeasonTicketEntity.TABLE_SEASON_TICKET}  WHERE CAST(substr(SeasonTicket_DateBegin, 7, 4) as INT) == :YEAR AND CAST(substr(SeasonTicket_DateBegin, 4, 2) as INT) == :MONTH ")
    suspend fun getAllWithDate(MONTH: Int, YEAR: Int): List<SeasonTicketEntity>
    */

    @Query("SELECT *   from ${SeasonTicketEntity.TABLE_SEASON_TICKET}  WHERE id = :idTicket LIMIT 1 ")
    suspend fun getSeasonTicketByIdTicket(idTicket: Long): SeasonTicketEntity

    @Query("SELECT * from ${SeasonTicketEntity.TABLE_SEASON_TICKET}  WHERE SeasonTicket_IdUser = :idUser ORDER BY SeasonTicket_DateEnd LIMIT 1 ")
    suspend fun getLastRowByIdUser(idUser: Long): SeasonTicketEntity?


    @Query("DELETE from ${SeasonTicketEntity.TABLE_SEASON_TICKET}  WHERE SeasonTicket_IdUser = :idUser")
    suspend fun deleteRowsByIdUser(idUser: Long)
}