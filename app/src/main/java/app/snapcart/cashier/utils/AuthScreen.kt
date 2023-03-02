package app.snapcart.cashier.utils

sealed class AuthScreen(val route:String){
    object  MainScreen: AuthScreen(route = "auth")
    object  OTPScreen: AuthScreen(route = "otp")
}
