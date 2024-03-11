package com.metehan.authentication.presentation.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehan.authentication.domain.repository.AuthRepository
import com.metehan.authentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    private val _sendEmailVerificationState = Channel<SignUpState>()
    val sendEmailVerificationState = _sendEmailVerificationState.receiveAsFlow()

    fun registerUser(name: String, email: String, password: String) = viewModelScope.launch {
        repository.registerUser(name, email, password).collect{ result ->
            when(result){
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }

    fun sendEmailVerification() = viewModelScope.launch {
        repository.sendEmailVerification().collect{ result ->
            when(result){
                is Resource.Success -> {
                    _sendEmailVerificationState.send(SignUpState(isSuccess = "We've sent you an email with a link to verify email."))
                }
                is Resource.Loading -> {
                    _sendEmailVerificationState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _sendEmailVerificationState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}