package com.melyseev.seasonticket72.screens.adduser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.seasonticket72.base.EventHandler
import com.melyseev.seasonticket72.data.user.UserEntity
import com.melyseev.seasonticket72.data.user.UserRepository
import com.melyseev.seasonticket72.screens.adduser.model.AddUserViewEvent
import com.melyseev.seasonticket72.screens.adduser.model.AddUserViewState
import com.melyseev.seasonticket72.screens.adduser.views.ErrorAddNewUser
import com.melyseev.seasonticket72.screens.adduser.views.InitialAddNewUser

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(), EventHandler<AddUserViewEvent> {

    private val _viewState: MutableLiveData<AddUserViewState> =  MutableLiveData(AddUserViewState.AddDisplay("", ""))
    val viewState: LiveData<AddUserViewState> = _viewState


    override fun obtainEvent(event: AddUserViewEvent) {
        when (val currentViewState = _viewState.value) {
            is AddUserViewState.AddDisplay -> reduce(event, currentViewState)
            AddUserViewState.ErrorDisplay -> reduceError(event)
        }
    }

    private fun reduceError(event: AddUserViewEvent) {
        when (event) {
            AddUserViewEvent.ErrorHappenedClick -> {
                _viewState.postValue( AddUserViewState.AddDisplay(name = "", surname = ""))
            }
        }
    }
    private fun reduce(event: AddUserViewEvent, currentState: AddUserViewState.AddDisplay) {
        when (event) {
            is AddUserViewEvent.OnChangeName -> {
                viewModelScope.launch {
                    _viewState.postValue( currentState.copy(name = event.name))
                }
            }

            is AddUserViewEvent.OnChangeSurname -> {
                viewModelScope.launch {
                    _viewState.postValue( currentState.copy(surname = event.surname))
                }
            }

            AddUserViewEvent.SaveClick -> {

                if(currentState.name.isBlank() || currentState.surname.isBlank()){
                    _viewState.postValue( AddUserViewState.ErrorDisplay )
                }else

                viewModelScope.launch {
                    //Loading progress bar

                    //database save new name or add
                    userRepository.addNewUser(
                        UserEntity(name = currentState.name, surname =  currentState.surname)
                    )

                    _viewState.postValue( AddUserViewState.SuccessDisplay(new_name = currentState.name))
                }
            }

            AddUserViewEvent.ErrorHappenedClick -> {
               // _viewState.postValue( currentState.copy( error = false))
                _viewState.postValue( AddUserViewState.ErrorDisplay )
            }

        }
    }
}