package com.metehan.authentication.presentation.main_screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.util.components.TopBar
import com.metehan.authentication.presentation.main_screen.components.ProfileContent

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Profile",
                signOut = {
                    viewModel.signOut()
                },
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding
            )
        }
    )
}