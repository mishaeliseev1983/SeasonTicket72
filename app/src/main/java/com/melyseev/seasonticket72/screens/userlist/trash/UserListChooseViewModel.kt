package com.melyseev.seasonticket72.screens.userlist.trash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.base.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListChooseViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel(), EventHandler<UserListChooseViewEvent>{

    private val _userListViewState: MutableLiveData<UserListChooseViewState> =
        MutableLiveData(UserListChooseViewState.Loading)
    val userListViewState: LiveData<UserListChooseViewState> = _userListViewState
    override fun obtainEvent(event: UserListChooseViewEvent) {
        TODO("Not yet implemented")
    }

/*
    override fun obtainEvent(event: UserListChooseViewEvent) {

        when (val currentState = _userListViewState.value) {
            is UserListChooseViewState.Loading -> {
                reduce(event, currentState = UserListChooseViewState.Loading)
            }
            is UserListChooseViewState.NoItems ->{
            }
            is UserListChooseViewState.Error ->{
            }
            is UserListChooseViewState.Display -> {

                /*viewModelScope.launch {
                    var nameSurnameSelectedUser: UserEntity
                    currentState.selected.let {
                        nameSurnameSelectedUser = userRepository.getUserNameSurnameById(it)
                    }
*/
                    reduce(
                        event,
                        currentState = UserListChooseViewState.Display(items = currentState.items)
                    )
               // }
            }

        }
    }


    private fun reduce(event: UserListChooseViewEvent, currentState: UserListChooseViewState.Display) {
        when (event) {
            UserListChooseViewEvent.EnterScreen -> {
                receiveUsersData()
            }
            is UserListChooseViewEvent.ChooseUser -> {
                viewModelScope.launch {
                    currentState.items.forEach {
                        it.isSelected = (it.id == event.selectedId)
                    }
                    _userListViewState.postValue(
                        UserListChooseViewState.Display(items = currentState.items, selected = event.selectedId))

                }
            }
        }
    }

    private fun reduce(event: UserListChooseViewEvent, currentState: UserListChooseViewState.Loading) {
        when (event) {
            UserListChooseViewEvent.EnterScreen -> {
                receiveUsersData()
            }
        }
    }


    private fun receiveUsersData() {

        _userListViewState.postValue( UserListChooseViewState.Loading)
        viewModelScope.launch {
            try {

                val users = userRepository.fetchUserList()
                val userListCardItem = users.map {
                    UserCardItemModel(it.id, it.name, "")
                }.toMutableList()


                _userListViewState.postValue(
                    UserListChooseViewState.Display(items = userListCardItem)
                )
            } catch (e: Exception) {

            }
        }
    }
    */
}
