package app.snapcart.cashier.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.snapcart.cashier.R

sealed class BottomNavItem(@StringRes var title: Int, @DrawableRes var icon: Int, var screenRoute: String) {

    object Sales : BottomNavItem(
        R.string.bottom_nav_title_sales,
        R.drawable.ic_sales,
        HomeScreen.SalesScreen.route
    )

    object Purchases : BottomNavItem(
        R.string.bottom_nav_title_purchases,
        R.drawable.ic_purchases,
        HomeScreen.PurchasesScreen.route
    )

    object Products : BottomNavItem(
        R.string.bottom_nav_title_products,
        R.drawable.ic_product,
        HomeScreen.ProductsScreen.route
    )

    object Reports : BottomNavItem(
        R.string.bottom_nav_title_reports,
        R.drawable.ic_reports,
        HomeScreen.ReportsScreen.route
    )

    object Bonus : BottomNavItem(
        R.string.bottom_nav_title_bonus,
        R.drawable.ic_bonus,
        HomeScreen.BonusScreen.route
    )
}
