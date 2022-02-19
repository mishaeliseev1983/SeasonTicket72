package com.melyseev.seasonticket72.screens.sellticket

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.melyseev.seasonticket72.App
import com.melyseev.seasonticket72.R
import com.melyseev.seasonticket72.base.EventHandler
import com.melyseev.seasonticket72.commonview.UserCardItemModel
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketEntity
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketRepository
import com.melyseev.seasonticket72.data.user.UserEntity
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.screens.sellticket.model.*
import com.melyseev.seasonticket72.utils.*
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.qualifiers.ApplicationContext


@HiltViewModel
class SellViewModel @Inject constructor(
    val seasonTicketRepository: SeasonTicketRepository,
    val userRepository: UserRepository,
    @ApplicationContext val context : Context
) : ViewModel(), EventHandler<SellViewEvent> {


    private var currentDate: Date = Calendar.getInstance().time
    private var currentCountExercises = 2
    var selectedUser: UserEntity? = null

    private val _sellViewState: MutableLiveData<SellViewState> = MutableLiveData(SellViewState.Display(
        TicketInfo()
    ))
    val sellViewState: LiveData<SellViewState> = _sellViewState

    override fun obtainEvent(event: SellViewEvent) {

    when(val currentState = sellViewState.value){
            is SellViewState.Display -> reduce(event, currentState)
            is SellViewState.DisplayUsers -> reduce(event, currentState)
            is SellViewState.DisplayConfirmTicket -> reduce(event, currentState)
            is SellViewState.DisplayMessage -> reduce(event, currentState)
        }
    }

    fun reduce(event: SellViewEvent, state: SellViewState.DisplayMessage) {

        when (event) {
            SellViewEvent.EnterScreen -> {
                displaySellView(  ticketInfo = TicketInfo())
            }
        }
    }

    fun reduce(event: SellViewEvent, state: SellViewState.DisplayConfirmTicket) {
        when (event) {
            SellViewEvent.EnterScreen -> {
                displaySellView( state.confirmTicketInfo ) }
            SellViewEvent.SaveEnterScreen -> {


                viewModelScope.launch {

                    var gson = Gson()
                    val listExercises = state.confirmTicketInfo.exercises
                    val jsonStringExercises = gson.toJson( listExercises )
                    val dateEnd = listExercises[listExercises.size - 1].date

                    val newTicket = SeasonTicketEntity(
                        idUser = state.confirmTicketInfo.idUserForSale,
                        dateBegin =  toSimpleString(state.confirmTicketInfo.dateBegin),
                        exercises = jsonStringExercises,
                        openStatus = true,
                        dateEnd = dateEnd
                    )
                    seasonTicketRepository.addSeasonTicket(seasonTicketEntity = newTicket)
                        //save
                        val message ="${state.confirmTicketInfo.nameSurname} - ${state.confirmTicketInfo.countExercise}"
                        selectedUser = null
                        _sellViewState.postValue(
                        SellViewState.DisplayMessage( message = message)
                    )
                }
            }
        }
    }


    fun reduce(event: SellViewEvent, state: SellViewState.DisplayUsers) {
        when (event) {
            is SellViewEvent.EnterScreen -> { displaySellView( state.ticketInfo) }
            is SellViewEvent.SelectUser -> {  displayUsers(state.ticketInfo) }
        }
    }


    fun reduce(event: SellViewEvent, state: SellViewState.Display){
        when (event) {
            is SellViewEvent.EnterScreen -> { displaySellView(state.fieldTicketInfo) }

            SellViewEvent.NextDayClicked -> performNextClick(state.fieldTicketInfo)
            SellViewEvent.PreviousDayClicked ->  performPreviousClick(state.fieldTicketInfo)
            SellViewEvent.ChangeDaysNumberClicked -> performChangeDaysClick(state.fieldTicketInfo)
            SellViewEvent.ShowUsers -> displayUsers(state.fieldTicketInfo)
            SellViewEvent.GetTicket -> getTicket(state.fieldTicketInfo)
        }
    }

    fun displayUsers(ticketInfo: TicketInfo){
       // _sellViewState.postValue(SellViewState.Loading)

        viewModelScope.launch {
            val userList =  userRepository.fetchUserList()
            withContext(Dispatchers.Main) {

                val allItems = mapToCardItem(userList)
                allItems.forEach{
                    if(selectedUser?.id == it.id)
                                    it.isSelected = true
                    else it.isSelected = false
                }
                ticketInfo.warning=""
                _sellViewState.postValue(SellViewState.DisplayUsers( items = allItems, ticketInfo = ticketInfo))
            }
        }
    }

    fun mapToCardItem(listUserCardItemModel: List<UserEntity>): List<UserCardItemModel> {
        return listUserCardItemModel.map { entity ->
            UserCardItemModel(id = entity.id, name = entity.name, surname = entity.surname)
        }
    }

    private fun performPreviousClick(ticketInfo: TicketInfo){
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        currentDate = calendar.time

        //val dateBegin= getTitleForADay()
        val hasNext = true

        _sellViewState.postValue(
            SellViewState.Display(
                TicketInfo(
                    nameSurname = ticketInfo.nameSurname,
                    countExercise =  ticketInfo.countExercise,
                    dateBegin = currentDate,
                    idUserForSale = ticketInfo.idUserForSale,
                    hasNext = hasNext))
        )
    }


    private fun displaySellView(ticketInfo: TicketInfo)
    {
        var nameSurname="?"
        var idUser=-1L
        selectedUser?.let {
           nameSurname=
                "${it.name}  ${it.surname} "
            idUser=it.id
        }
        //state.newTicketInfo.nameSurname = nameSurname
        //state.newTicketInfo.idUserForSale = idUser

        _sellViewState.postValue(
           SellViewState.Display(TicketInfo(

               nameSurname = nameSurname,
               dateBegin = ticketInfo.dateBegin,
               hasNext = ticketInfo.hasNext,
               idUserForSale = idUser,
               countExercise = ticketInfo.countExercise,
               warning = ticketInfo.warning
           ))
        )

    }


    private fun performNextClick(ticketInfo: TicketInfo){
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        currentDate = calendar.time

        val todayCalendar = Calendar.getInstance()
        val todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH)
        val todayMonth = todayCalendar.get(Calendar.MONTH)
        val todayYear = todayCalendar.get(Calendar.YEAR)

        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)

        var hasNext = true
        if(currentDay == todayDay && currentMonth == todayMonth &&
            currentYear == todayYear)  hasNext = false


        //val dateBegin= getTitleForADay()
        _sellViewState.postValue(
            SellViewState.Display(
                TicketInfo(
                    nameSurname =  ticketInfo.nameSurname,
                    countExercise =  ticketInfo.countExercise,
                    dateBegin = currentDate,
                    idUserForSale = ticketInfo.idUserForSale,
                    hasNext = hasNext))
        )

    }


    private fun performChangeDaysClick(ticketInfo: TicketInfo){
        if (currentCountExercises == 4)
            currentCountExercises = 2
        else
            if (currentCountExercises == 2)
                currentCountExercises = 4
        //state.newTicketInfo.countExercise = currentCountExercises
        _sellViewState.postValue(
            SellViewState.Display(
                TicketInfo(
                    nameSurname =   ticketInfo.nameSurname,
                    countExercise = currentCountExercises,
                    dateBegin =     ticketInfo.dateBegin,
                    idUserForSale = ticketInfo.idUserForSale,
                    hasNext =       ticketInfo.hasNext))
        )

    }


    private fun getTicket(ticketInfo: TicketInfo){

        var warning = ""
        if(ticketInfo.idUserForSale == -1L) {
            warning = "Error: please, select user"
            _sellViewState.postValue(SellViewState.DisplayMessage(message = warning, success = false))
            return
        }

        //MESSAGE - SELECT USER

        /*
        if(warning.isNotEmpty()) {
            _sellViewState.postValue(
                SellViewState.Display(
                    TicketInfo(
                        nameSurname = ticketInfo.nameSurname,
                        countExercise = ticketInfo.countExercise,
                        dateBegin = ticketInfo.dateBegin,
                        idUserForSale = ticketInfo.idUserForSale,
                        hasNext = ticketInfo.hasNext,
                        warning = warning
                    )
                )
            )
            return
        }

         */



            viewModelScope.launch {
                _sellViewState.postValue(SellViewState.Loading)

                //CHECK BEGIN DATE
                val lastRow = seasonTicketRepository.fetchSeasonTicketByIdUser(ticketInfo.idUserForSale )
                var dateError = false
                var statusOpenError = false

                lastRow?.let {
                    if(lastRow.openStatus) statusOpenError = true
                    val lastRowEndDate =    it.dateEnd.fromStringToDate()
                    val beginDate =  ticketInfo.dateBegin
                    if(beginDate < lastRowEndDate) dateError = true
                }




                if(statusOpenError) warning = context.resources.getString(R.string.error_last_ticket_still_open)

                    //"Last ticket is still open. You need to close ticket."
                else
                if(dateError) warning =
                        "${toSimpleString(ticketInfo.dateBegin) }  ${context.resources.getString(R.string.error_bad_data)}"


                if(statusOpenError || dateError) {
                    _sellViewState.postValue(
                        SellViewState.DisplayMessage(
                            message = warning,
                            success = false
                        )
                    )
                    return@launch
                }
                /*
                if(warning.isNotEmpty()){
                    selectedUser = null
                    displaySellView( ticketInfo = TicketInfo(
                        nameSurname = ticketInfo.nameSurname,
                        dateBegin = ticketInfo.dateBegin,
                        countExercise = ticketInfo.countExercise,
                        idUserForSale = ticketInfo.idUserForSale,
                        hasNext = ticketInfo.hasNext,
                        warning = warning,

                    ) )
                    return@launch
                }

                 */



                val exercises = getAllDaysSold(ticketInfo.dateBegin, ticketInfo.countExercise)
                _sellViewState.postValue(
                    SellViewState.DisplayConfirmTicket(
                        TicketInfo(
                            nameSurname = ticketInfo.nameSurname,
                            dateBegin = ticketInfo.dateBegin,
                            countExercise = ticketInfo.countExercise,
                            idUserForSale = ticketInfo.idUserForSale,
                            hasNext = ticketInfo.hasNext,
                            warning = ticketInfo.warning,
                            exercises = exercises
                        )
                    )
                )
            }


    }


}