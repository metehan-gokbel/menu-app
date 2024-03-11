package com.metehan.authentication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metehan.authentication.presentation.forgot_password_screen.ForgotPasswordScreen
import com.metehan.authentication.presentation.login_screen.SignInScreen
import com.metehan.authentication.presentation.main_screen.MainScreen
import com.metehan.authentication.presentation.register_screen.SignUpScreen
import com.metehan.authentication.presentation.verify_email_screen.VerifyEmailScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(Screens.ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(Screens.SignUpScreen.route)
                }
            )
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screens.VerifyEmailScreen.route) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(Screens.MainScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
        composable(route = Screens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}