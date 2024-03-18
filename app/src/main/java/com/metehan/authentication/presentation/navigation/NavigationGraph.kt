package com.metehan.authentication.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.metehan.authentication.presentation.forgot_password_screen.ForgotPasswordScreen
import com.metehan.authentication.presentation.login_screen.SignInScreen
import com.metehan.authentication.presentation.main_screen.MainScreen
import com.metehan.authentication.presentation.menu_detail_screen.MenuDetailScreen
import com.metehan.authentication.presentation.menu_screen.MenuScreen
import com.metehan.authentication.presentation.register_screen.SignUpScreen
import com.metehan.authentication.presentation.send_bill_screen.SendBillScreen
import com.metehan.authentication.presentation.verify_email_screen.VerifyEmailScreen
import com.metehan.authentication.util.Constants.MERCHANT_ID

@Composable
fun NavigationGraph(
    navController: NavHostController
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
                },
                navigateToMainScreen = {
                    navController.navigate(Screens.MainScreen.route)
                },
                navigateToMainScreenWithAnonymous = {
                    navController.navigate(Screens.MainScreen.route)
                },
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
        composable(route = Screens.MenuScreen.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MenuScreen(navController = navController)
            }
        }
        composable(route = Screens.SendBillScreen.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SendBillScreen(navController = navController)
            }
        }
        composable(route = Screens.MainScreen.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen(
                    navigateToMenuScreen = {
                        navController.navigate(Screens.MenuScreen.route)
                    },
                    navigateToSendBillScreen = {
                        navController.navigate(Screens.SendBillScreen.route)
                    },
//                    navigateToSignInScreen = {
//                        navController.navigate(Screens.SignInScreen.route)
//                    }
                )
            }
        }
        composable(route = Screens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screens.MenuDetailScreen.route + "/{${MERCHANT_ID}}",
            arguments = listOf(
                navArgument(MERCHANT_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val merchantId = remember {
                it.arguments?.getString(MERCHANT_ID)
            }
            MenuDetailScreen(
                merchantId = merchantId ?: ""
            )
        }
    }

}