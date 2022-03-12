package com.melyseev.seasonticket72.screens.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.base.EventHandler
import com.melyseev.seasonticket72.commonview.UserCardItemModel
import com.melyseev.seasonticket72.screens.userlist.model.UserListViewEvent
import com.melyseev.seasonticket72.screens.userlist.model.UserListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel(), EventHandler<UserListViewEvent> {

    private val _userListViewState: MutableLiveData<UserListViewState> =
        MutableLiveData(UserListViewState.Loading)
    val userListViewState: LiveData<UserListViewState> = _userListViewState

    /*
    init {
        obtainEvent(event = UserListViewEvent.EnterScreen)
    }
     */

    override fun obtainEvent(event: UserListViewEvent) {

        when (val currentState = _userListViewState.value) {
            is UserListViewState.Loading -> {
                reduce(event, currentState = UserListViewState.Loading)
            }
            is UserListViewState.NoItems -> {
            }
            is UserListViewState.Error -> {
            }
            is UserListViewState.Display -> {
               reduce(event, currentState = UserListViewState.Display(items = currentState.items))
            }
            /*
            is UserListViewState.AddUserDisplay -> {
                reduce(event, currentState = UserListViewState.AddUserDisplay(name = currentState.name,
                    surname = currentState.surname))
            }
            */

        }
    }


    private fun reduce(event: UserListViewEvent, currentState: UserListViewState.Display) {
        when (event) {
            UserListViewEvent.EnterScreen -> {
                receiveUsersData()
                /*
                if(currentState.items.isEmpty()){
                    receiveUsersData()
                }
                else{
                    _userListViewState.postValue(
                        UserListViewState.Display(items = currentState.items)
                    )
                }
            }*/
            }

            /*
            is UserListViewEvent.AddUser ->{
                _userListViewState.postValue(
                    UserListViewState.AddUserDisplay(name = "", surname = "")
                )

            }
            */
        }
    }

    private fun reduce(event: UserListViewEvent, currentState: UserListViewState.Loading) {
        when (event) {
            UserListViewEvent.EnterScreen -> {
                receiveUsersData()
            }
        }
    }

    private fun receiveUsersData() {

        //_userListViewState.postValue( UserListViewState.Loading )


        viewModelScope.launch {
            try {


                val users = userRepository.fetchUserList()
                val userListCardItem = users.map {
                    UserCardItemModel(it.id, it.name, it.surname)
                }


                _userListViewState.postValue(
                    UserListViewState.Display(items = userListCardItem)
                )
            } catch (e: Exception) {

            }
        }
    }

    /*
    private fun reduce(event: UserListViewEvent, currentState: UserListViewState.AddUserDisplay) {
        when (event) {
            is UserListViewEvent.OnChangeName ->{
                viewModelScope.launch {
                    _userListViewState.postValue( currentState.copy(name = event.name))
                }
            }

            is UserListViewEvent.OnChangeSurname ->{
                viewModelScope.launch {
                    _userListViewState.postValue( currentState.copy(surname = event.surname))
                }
            }

            UserListViewEvent.OnSaveNewUser -> {
                viewModelScope.launch {
                    _userListViewState.postValue(currentState.copy())
                }
            }
        }
    }
     */


}

