package com.metehan.authentication.presentation.register_screen

import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.presentation.register_screen.components.SendEmailVerification
import com.metehan.authentication.presentation.register_screen.components.SignUp
import com.metehan.authentication.presentation.register_screen.components.SignUpContent
import com.metehan.authentication.presentation.register_screen.components.SignUpTopBar

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {

        },
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = { name, email, password ->
                    viewModel.registerUser(name, email, password)
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            Toast.makeText(
                context,
                "We've sent you an email with a link to verify email.",
                Toast.LENGTH_LONG
            ).show()
        }
    )

    SendEmailVerification()
}

