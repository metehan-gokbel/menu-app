package com.metehan.authentication.presentation.login_screen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.metehan.authentication.presentation.login_screen.components.OneTapSignIn
import com.metehan.authentication.presentation.login_screen.components.SignIn
import com.metehan.authentication.presentation.login_screen.components.SignInContent
import com.metehan.authentication.presentation.login_screen.components.SignInWithGoogle

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToMainScreen: () -> Unit,
    navigateToMainScreenWithAnonymous: () -> Unit,
) {
    Scaffold(
        content = { padding ->
            SignInContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.loginUser(email, password)
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen,
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                },
                navigateToAnonymous = {
                    viewModel.loginAnonymously()
                }
            )
        }
    )
    SignIn()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToMainScreen()
            }
        }
    )
}


