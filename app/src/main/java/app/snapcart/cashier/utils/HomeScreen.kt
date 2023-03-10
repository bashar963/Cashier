package app.snapcart.cashier.utils

sealed class HomeScreen(val route: String) {

    object SalesScreen : AuthScreen(route = "sales")

    object PurchasesScreen : AuthScreen(route = "purchases")

    object ProductsScreen : AuthScreen(route = "products")

    object ReportsScreen : AuthScreen(route = "reports")

    object BonusScreen : AuthScreen(route = "bonus")
}
