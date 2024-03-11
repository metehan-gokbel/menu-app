package com.metehan.authentication.presentation.forgot_password_screen

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.presentation.forgot_password_screen.components.ForgotPassword
import com.metehan.authentication.presentation.forgot_password_screen.components.ForgotPasswordContent
import com.metehan.authentication.util.components.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(
                        text = "Forgot password"
                    )
                },
                navigationIcon = {
                    BackIcon(
                        navigateBack = navigateBack
                    )
                }
            )
        },
        content = { padding ->
            ForgotPasswordContent(
                padding = padding,
                sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            Toast.makeText(
                context,
                "We've sent you an email with a link to reset the password.",
                Toast.LENGTH_LONG
            ).show()
        },

    )
}