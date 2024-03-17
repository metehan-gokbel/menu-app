package com.metehan.authentication.presentation.verify_email_screen

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.presentation.main_screen.MainViewModel
import com.metehan.authentication.presentation.verify_email_screen.components.ReloadUser
import com.metehan.authentication.presentation.verify_email_screen.components.VerifyEmailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(
                        text = "Verify Email"
                    )
                }
            )
        },
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToProfileScreen()
            } else {
                Toast.makeText(
                    context,
                    "Your email is not verified.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )
}