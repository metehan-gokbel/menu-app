package com.metehan.authentication.presentation.verify_email_screen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.util.components.ProgressBar
import com.metehan.authentication.presentation.main_screen.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun ReloadUser(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val state = viewModel.reloadUserState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if(state.value?.isLoading == true){
        ProgressBar()
    }
    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                navigateToProfileScreen()
            }
        }
    }
    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}