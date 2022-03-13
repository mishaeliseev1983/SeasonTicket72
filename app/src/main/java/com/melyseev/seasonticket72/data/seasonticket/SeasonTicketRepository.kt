package com.melyseev.seasonticket72.data.seasonticket


import java.util.*
import javax.inject.Inject

class SeasonTicketRepository @Inject constructor(
    private val seasonTicketDao: SeasonTicketDao
) {

    suspend fun addSeasonTicket(seasonTicketEntity: SeasonTicketEntity) {
        seasonTicketDao.insert(seasonTicketEntity)
    }

    suspend fun fetchSeasonTicketList(): List<SeasonTicketEntity> =
        seasonTicketDao.getAll()

/*
    suspend fun fetchSeasonTicketListWithFormatDate(MONTH: Int, YEAR: Int): List<SeasonTicketEntity> =
        seasonTicketDao.getAllWithDate(MONTH, YEAR)
*/

    suspend fun fetchSeasonTicketsByUserByDate(MONTH: Int, YEAR: Int, idUser: Long): List<SeasonTicketEntity> =
        seasonTicketDao.fetchSeasonTicketsByUserByDate(MONTH, YEAR, idUser)

    suspend fun fetchSeasonTicketByIdTicket(idTicket: Long): SeasonTicketEntity=
        seasonTicketDao.getSeasonTicketByIdTicket(idTicket)

    suspend fun fetchSeasonTicketByIdUser(idUser: Long): SeasonTicketEntity?=
        seasonTicketDao.getLastRowByIdUser(idUser)

    suspend fun update(entity: SeasonTicketEntity) =
        seasonTicketDao.update(entity = entity)

    suspend fun deleteRowsByIdUser(idUser: Long) =
        seasonTicketDao.deleteRowsByIdUser(idUser = idUser)

}