package com.metehan.authentication.presentation.login_screen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.presentation.login_screen.SignInViewModel
import com.metehan.authentication.util.components.ProgressBar
import kotlinx.coroutines.launch

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if(state.isLoading){
        ProgressBar()
    }
    LaunchedEffect(key1 = state.isSuccess) {
        scope.launch {
            if (state.isSuccess?.isNotEmpty() == true) {
                Unit
            }
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