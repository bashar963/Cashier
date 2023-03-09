package app.snapcart.cashier.utils

sealed class RegisterScreen(val route: String) {

    object SuccessScreen : AuthScreen(route = "success")

    object StoreProfileScreen : AuthScreen(route = "store_profile")

    object StoreAddressScreen : AuthScreen(route = "store_address")

    object StoreMapScreen : AuthScreen(route = "store_map")
}
