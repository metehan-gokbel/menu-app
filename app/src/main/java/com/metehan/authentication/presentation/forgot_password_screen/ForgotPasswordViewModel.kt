package com.metehan.authentication.presentation.forgot_password_screen

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
class ForgotPasswordViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(){

    private val _passwordResetEmailState = Channel<SignUpState>()
    val passwordResetEmailState = _passwordResetEmailState.receiveAsFlow()

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        repository.sendPasswordResetEmail(email).collect{result ->
            when(result){
                is Resource.Success -> {
                    _passwordResetEmailState.send(SignUpState(isSuccess = "Password Reset is Success"))
                }
                is Resource.Loading -> {
                    _passwordResetEmailState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _passwordResetEmailState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}