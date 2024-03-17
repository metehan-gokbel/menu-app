package com.metehan.authentication.presentation.navigation

sealed class Screens(val route: String) {
    data object SignInScreen : Screens(route = "SignIn_Screen")
    data object SignUpScreen : Screens(route = "SignUp_Screen")
    data object MainScreen : Screens(route = "Main_Screen")
    data object MenuScreen : Screens(route = "Menu_Screen")
    data object SendBillScreen : Screens(route = "Send_Bill_Screen")
    data object MenuDetailScreen : Screens(route = "Menu_Detail_Screen")
    data object VerifyEmailScreen : Screens(route = "Verify_Email_Screen")
    data object ForgotPasswordScreen : Screens(route = "Forgot_Password_Screen")
}