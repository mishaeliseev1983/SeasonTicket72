package com.melyseev.seasonticket72.screens.seasonticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.melyseev.seasonticket72.base.EventHandler
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketRepository
import com.melyseev.seasonticket72.data.user.UserEntity
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewEvent
import com.melyseev.seasonticket72.screens.seasonticket.model.SeasonTicketViewState
import com.melyseev.seasonticket72.screens.seasonticket.model.UserExercises
import com.melyseev.seasonticket72.utils.*

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class SeasonTicketViewModel @Inject constructor(
    val userRepository: UserRepository,
    val seasonTicketRepository: SeasonTicketRepository
) : ViewModel(), EventHandler<SeasonTicketViewEvent> {

    //private var currentDate: Date = Calendar.getInstance().time
    private val _seasonTicketViewState: MutableLiveData<SeasonTicketViewState> = MutableLiveData(SeasonTicketViewState.Loading)
    val seasonTicketViewState: LiveData<SeasonTicketViewState> = _seasonTicketViewState

    private val mapOpenStatusTicket = mutableMapOf<Long, Boolean>()
    lateinit var listUsers: List<UserEntity>
    //lateinit var listTickets: List<SeasonTicketEntity>

/*
    override fun obtainEvent(event: SeasonTicketViewEvent) {
        when(val currentState = seasonTicketViewState.value){
            is SeasonTicketViewState.Loading -> reduce(event, currentState)
            is SeasonTicketViewState.Display -> reduce(event, currentState)
        }
    }

    fun reduce(event: SeasonTicketViewEvent, state: SeasonTicketViewState.Loading){
        displaySeasonTickets()
    }

    fun reduce(event: SeasonTicketViewEvent, state: SeasonTicketViewState.Display){
        when (event) {
            is SeasonTicketViewEvent.EnterScreen -> {
                displaySeasonTickets()
            }
            SeasonTicketViewEvent.NextMonthClicked->{
                val calendar = Calendar.getInstance()
                calendar.time =  state.currentMonth
                calendar.add(Calendar.MONTH, 1)
                state.currentMonth = calendar.time

                _seasonTicketViewState.postValue(
                    SeasonTicketViewState.Display(currentMonth = calendar.time, exercises = state.exercises, users = state.users)
                )


            }
            SeasonTicketViewEvent.PreviousMonthClicked->{
                val calendar = Calendar.getInstance()
                calendar.time =  state.currentMonth
                calendar.add(Calendar.MONTH, -1)
                state.currentMonth = calendar.time

                _seasonTicketViewState.postValue(
                    SeasonTicketViewState.Display(currentMonth = calendar.time, exercises = state.exercises, users = state.users)
                )
            }

        }
    }

 */



    override fun obtainEvent(event: SeasonTicketViewEvent) {
        when(val currentState = seasonTicketViewState.value){
            is SeasonTicketViewState.Display -> reduce(event, currentState)
            is SeasonTicketViewState.DisplayTicketCloseOpen ->reduce(event, currentState)
            SeasonTicketViewState.Loading -> reduce(event)
            SeasonTicketViewState.DisplayUserGuide -> reduce(event)

        }
    }
    fun reduce(event: SeasonTicketViewEvent){
              displaySeasonTickets( currentDate =  Date())
    }

    fun reduce(event: SeasonTicketViewEvent, state: SeasonTicketViewState.Display){
        when (event) {
            is SeasonTicketViewEvent.EnterScreen -> {
                displaySeasonTickets(state.currentMonth)
            }

            SeasonTicketViewEvent.PreviousMonthClicked ->{
                _seasonTicketViewState.postValue(
                    SeasonTicketViewState.Loading
                )

                val calendar = Calendar.getInstance()
                calendar.time =  state.currentMonth
                calendar.add(Calendar.MONTH, -1)
                displaySeasonTickets(currentDate =  calendar.time)
            }

            SeasonTicketViewEvent.NextMonthClicked ->{
                _seasonTicketViewState.postValue(
                    SeasonTicketViewState.Loading
                )

                val calendar = Calendar.getInstance()
                calendar.time =  state.currentMonth
                calendar.add(Calendar.MONTH, 1)
                displaySeasonTickets(currentDate =  calendar.time)
            }


            is SeasonTicketViewEvent.EditTicket ->{
               changeStatusCard(state.currentMonth, event.idTicket, event.date)
            }

            is SeasonTicketViewEvent.ShowTicketForClose -> {


                viewModelScope.launch {
                    val closeTicketId = event.idTicket
                    val closeSeasonTicket = seasonTicketRepository.fetchSeasonTicketByIdTicket(
                        idTicket = closeTicketId
                    )
                    val exercises = mapStringExercisesToListOneExercises(closeSeasonTicket.exercises)
                    val nameSurname = getNameSurname(listUsers, closeSeasonTicket.idUser)


                    _seasonTicketViewState.postValue(
                        SeasonTicketViewState.DisplayTicketCloseOpen(
                            id = event.idTicket,
                            listExercises = exercises,
                            nameSurname = nameSurname,
                            currentMonth = state.currentMonth,
                            openStatus = closeSeasonTicket.openStatus
                            )
                    )
                }
            }
        }
    }

    fun reduce(event: SeasonTicketViewEvent, state: SeasonTicketViewState.DisplayTicketCloseOpen) {

        when (event) {

            SeasonTicketViewEvent.EnterScreen -> {
                displaySeasonTickets(state.currentMonth)
            }

            is SeasonTicketViewEvent.OpenSeasonTicket -> {
                changeOpenStatusTicket(state = state, status = true)
            }

            is SeasonTicketViewEvent.CloseSeasonTicket -> {
                changeOpenStatusTicket(state = state, status = false)
            }

        }
    }


    private fun changeOpenStatusTicket(state: SeasonTicketViewState.DisplayTicketCloseOpen, status: Boolean){
        viewModelScope.launch {
            val ticketForClose = seasonTicketRepository.fetchSeasonTicketByIdTicket(state.id)
            ticketForClose.openStatus = status
            seasonTicketRepository.update(ticketForClose)
            val exercises = mapStringExercisesToListOneExercises(ticketForClose.exercises)
            _seasonTicketViewState.postValue(SeasonTicketViewState.DisplayTicketCloseOpen(
                id = state.id,
                nameSurname = state.nameSurname,
                currentMonth = state.currentMonth,
                listExercises = exercises,
                openStatus = ticketForClose.openStatus
            ))
        }

    }

    private fun displaySeasonTickets(currentDate: Date) {


        val calendarChoose = Calendar.getInstance()
        calendarChoose.time = currentDate
        val month = calendarChoose.get(Calendar.MONTH) + 1
        val year  = calendarChoose.get(Calendar.YEAR)



        viewModelScope.launch {

            if(seasonTicketRepository.fetchSeasonTicketList().isEmpty()){
                _seasonTicketViewState.postValue(
                    SeasonTicketViewState.DisplayUserGuide
                )
                return@launch
            }
            val listUserTickets = mutableListOf<UserExercises>()
            listUsers =  userRepository.fetchUserList()

            for (index in listUsers.indices) {

               val user = listUsers[index]

               val exercisesInMonth = getAllDaysExercises(currentDate)
               val  listTicketsByUser = seasonTicketRepository.fetchSeasonTicketsByUserByDate(
                    MONTH = month,
                    YEAR = year,
                    idUser = user.id
                )


                var idTicket = -1L

                if(listTicketsByUser.isEmpty()) continue
                listTicketsByUser.forEach { ticket ->
                    val userTicketExercises =  mapStringExercisesToListOneExercises( ticket.exercises )
                    idTicket = ticket.id
                    var index = 0
                    while (index < exercisesInMonth.size) {

                        var find = false
                        var status = 0

                        if (userTicketExercises.any {
                                val d1 = it.date
                                val d2 = exercisesInMonth[index].date
                                status = it.status
                                d1 == d2
                            }) find = true

                        if (find) {
                            exercisesInMonth[index].status = status
                            exercisesInMonth[index].idTicket = ticket.id
                            mapOpenStatusTicket[ticket.id] = ticket.openStatus
                        }
                        index++
                    }

                }

                val userExercises = UserExercises( idUser = user.id, exercises = exercisesInMonth, idTicketOpenStatus = idTicket)
                listUserTickets.add(userExercises)
            }

            _seasonTicketViewState.postValue(
                    SeasonTicketViewState.Display(
                        currentMonth = currentDate,
                        exercises = listUserTickets,
                        users = listUsers,
                        mapOpenStatusTicket = mapOpenStatusTicket
                    )
                )

        }
    }


    private fun changeStatusCard(currentMonth: Date, idSelectedTicket: Long, date: String){

        if(idSelectedTicket==-1L) return
        val calendarChoose = Calendar.getInstance()
        calendarChoose.time = currentMonth

        val openStatus = mapOpenStatusTicket[idSelectedTicket]
        if(openStatus == null || openStatus==false)return
        viewModelScope.launch {

            val seasonTicket = seasonTicketRepository.fetchSeasonTicketByIdTicket(
                idTicket = idSelectedTicket
            )
            val exercises   = mapStringExercisesToListOneExercises(seasonTicket.exercises)

            exercises.findLast { oneExercise -> oneExercise.date == date }?.let {

               // findExercise->
               // findExercise.openStatusTicket?.let {
                    //if(!it) return@let


                //if(it.openStatusTicket != null && it.openStatusTicket == true) {

                    var newStatusExercise = getNewStatusExercise(it.status)

                    it.status = newStatusExercise

                    seasonTicket.exercises = Gson().toJson(exercises)

                    //update row
                    seasonTicketRepository.update(seasonTicket)

                    displaySeasonTickets(currentMonth)
                //}

            }

           // var newStatusExercise = getNewStatusExercise( exercises[ selectedIndexCard ].status  )

/*
            //exercises[ selectedIndexCard ].status = newStatusExercise
            seasonTicket.exercises =  Gson().toJson(exercises)

            //update row
            seasonTicketRepository.update(seasonTicket)

            //read rows
            listTickets = seasonTicketRepository.fetchSeasonTicketListWithFormatDate(MONTH = month, YEAR = year)

            val seasonExercisesObject = getUserTicketObjectsExercise(listTickets)

            _seasonTicketViewState.postValue(
                SeasonTicketViewState.Display(
                    currentMonth = currentMonth,
                    exercises = seasonExercisesObject,
                    users = listUsers
                    )
            )

*/
    }
    }


    /*
    private fun displayUserGuide(currentDate: Date): Boolean {

        var needUserGuide = false
        val job = viewModelScope.launch {

            val needUserGuide = seasonTicketRepository.fetchSeasonTicketList().isEmpty()


            return@launch
        }
        return false
    }

     */
}