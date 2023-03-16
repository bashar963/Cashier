package app.snapcart.cashier.utils

enum class HomeScreen(val route: String) {
    SalesScreen(route = "sales"),
    PurchasesScreen(route = "purchases"),
    ProductsScreen(route = "products"),
    ReportsScreen(route = "reports"),
    BonusScreen(route = "bonus")
}
