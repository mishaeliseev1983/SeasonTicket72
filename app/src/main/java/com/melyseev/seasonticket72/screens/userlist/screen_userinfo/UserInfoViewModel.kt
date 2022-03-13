package com.melyseev.seasonticket72.screens.userlist.screen_userinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.seasonticket72.base.EventHandler
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketRepository
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model.UserInfoViewEvent
import com.melyseev.seasonticket72.screens.userlist.screen_userinfo.model.UserInfoViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val seasonTicketRepository: SeasonTicketRepository,

    ): ViewModel(), EventHandler<UserInfoViewEvent> {

    private val _viewState: MutableLiveData<UserInfoViewState> =
        MutableLiveData(UserInfoViewState.InfoDisplay("", ""))
    val viewState: LiveData<UserInfoViewState> = _viewState

    override fun obtainEvent(event: UserInfoViewEvent) {
        when (val currentState = viewState.value) {
            is UserInfoViewState.InfoDisplay -> {
                reduce(event = event, currentState = currentState)
            }
            is UserInfoViewState.AskDeleteDisplay ->{
                reduce(event = event, currentState = currentState)
            }

        }
    }

    fun postNewState(userId: Long) {
        viewModelScope.launch {
            val userEntity = userRepository.getUserNameSurnameById(userId)
            _viewState.postValue(UserInfoViewState.InfoDisplay(name = userEntity.name, surname = userEntity.surname, id = userId))
        }
    }

    private fun reduce(event: UserInfoViewEvent, currentState: UserInfoViewState.AskDeleteDisplay) {
        when (event) {
            is UserInfoViewEvent.DeleteClick -> {

                _viewState.postValue(
                    UserInfoViewState.Loading
                )

                viewModelScope.launch {
                    val userEntity = userRepository.getUserNameSurnameById(event.id)
                    seasonTicketRepository.deleteRowsByIdUser(idUser =  event.id)
                    userRepository.deleteUser(userEntity = userEntity)

                    _viewState.postValue(
                        UserInfoViewState.UserList
                    )
                }

            }
        }
    }

    private fun reduce(event: UserInfoViewEvent, currentState: UserInfoViewState.InfoDisplay) {
        when (event) {
           is UserInfoViewEvent.SaveClick -> {
               _viewState.postValue(
                      UserInfoViewState.Loading
               )
               viewModelScope.launch {

                   val userEntity = userRepository.getUserNameSurnameById(event.id)
                   userEntity.name= event.name
                   userEntity.surname= event.surname
                   userRepository.updateUser(userEntity = userEntity)


                   _viewState.postValue(
                       UserInfoViewState.SuccessDisplay
                   )
                   /*
                   val userEntityUpdated = userRepository.getUserNameSurnameById(event.id)
                   _viewState.postValue(
                       UserInfoViewState.InfoDisplay(name = userEntityUpdated.name,
                           surname = userEntityUpdated.surname))

                    */
               }
            }


            is UserInfoViewEvent.DeleteClick ->{

                viewModelScope.launch {
                    _viewState.postValue(UserInfoViewState.Loading)
                    val userEntity = userRepository.getUserNameSurnameById(id = event.id)
                    _viewState.postValue(
                        UserInfoViewState.AskDeleteDisplay( id = event.id, nameSurname = "${userEntity.name} ${userEntity.surname}")
                    )
                }

            }


            is UserInfoViewEvent.OnChangeName ->{
                _viewState.postValue(
                    currentState.copy(name = event.name)
                )
            }
            is UserInfoViewEvent.OnChangeSurname -> {
                _viewState.postValue(
                    currentState.copy(surname = event.surname)
                )
            }

        }
    }

}