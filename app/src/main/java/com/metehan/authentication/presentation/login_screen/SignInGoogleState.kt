package com.metehan.authentication.presentation.login_screen

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class SignInGoogleState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean? = false,
    val isError: String? = ""
)