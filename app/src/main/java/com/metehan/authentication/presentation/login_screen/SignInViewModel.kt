package com.metehan.authentication.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.metehan.authentication.domain.repository.AuthRepository
import com.metehan.authentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {

//    private val _signInState = Channel<SignInState>()
//    val signInState = _signInState.receiveAsFlow()

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _oneTapSignState = MutableStateFlow(OneTapSignState())
    val oneTapSignState = _oneTapSignState.asStateFlow()

    private val _signInGoogleState = MutableStateFlow(SignInGoogleState())
    val signInGoogleState = _signInGoogleState.asStateFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _state.emit(SignInState(isSuccess = "Sign In Success"))
                    val token = result.data?.let { getTokenFromAuthResult(it) }
                }

                is Resource.Loading -> {
                    _state.emit(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _state.emit(SignInState(isError = result.message))
                }
            }
        }
    }

    fun getTokenFromAuthResult(authResult: AuthResult): String {
        val token = authResult.user?.getIdToken(false)?.result?.token
        return token ?: ""
    }

    fun oneTapSignIn() = viewModelScope.launch {
        repository.oneTapSignInWithGoogle().collect { result ->
            when (result) {
                is Resource.Success -> {
                    _oneTapSignState.emit(OneTapSignState(isSuccess = result.data))
                }

                is Resource.Loading -> {
                    _oneTapSignState.emit(OneTapSignState(isLoading = true))
                }

                is Resource.Error -> {
                    _oneTapSignState.emit(OneTapSignState(isError = result.message))
                }
            }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        repository.firebaseSignInWithGoogle(googleCredential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInGoogleState.emit(SignInGoogleState(isSuccess = result.data))
                }

                is Resource.Loading -> {
                    _signInGoogleState.emit(SignInGoogleState(isLoading = true))
                }

                is Resource.Error -> {
                    _signInGoogleState.emit(SignInGoogleState(isError = result.message))
                }
            }
        }
    }
}