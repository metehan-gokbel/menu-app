package com.metehan.authentication.presentation.login_screen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.metehan.authentication.presentation.login_screen.SignInViewModel
import com.metehan.authentication.util.components.ProgressBar
import kotlinx.coroutines.launch

@Composable
fun OneTapSignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    val state by viewModel.oneTapSignState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if(state.isLoading){
        ProgressBar()
    }

    state.isSuccess?.let {
        LaunchedEffect(it) {
            launch(it)
        }
    }

    LaunchedEffect(key1 = state.isError) {
        scope.launch {
            if (state.isError?.isNotEmpty() == true) {
                val error = state.isError
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}