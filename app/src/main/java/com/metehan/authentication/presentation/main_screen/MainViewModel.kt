package com.metehan.authentication.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehan.authentication.domain.repository.AuthRepository
import com.metehan.authentication.presentation.register_screen.SignUpState
import com.metehan.authentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private val _reloadUserState = Channel<SignUpState>()
    val reloadUserState = _reloadUserState.receiveAsFlow()

    val isEmailVerified get() = repository.currentUser?.isEmailVerified ?: false

    fun signOut() = repository.signOut()

    fun reloadUser() = viewModelScope.launch {
        repository.reloadFirebaseUser().collect{ result ->
            when(result){
                is Resource.Success -> {
                    _reloadUserState.send(SignUpState(isSuccess = "Reload User Success"))
                }
                is Resource.Loading -> {
                    _reloadUserState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _reloadUserState.send(SignUpState(isError = result.message))
                }
            }
        }
    }

    fun getAuthState() = repository.getAuthState(viewModelScope)
}