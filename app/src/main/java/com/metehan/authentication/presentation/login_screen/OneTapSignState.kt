package com.metehan.authentication.presentation.login_screen

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class OneTapSignState(
    val isLoading: Boolean = false,
    val isSuccess: BeginSignInResult? = null,
    val isError: String? = ""
)
