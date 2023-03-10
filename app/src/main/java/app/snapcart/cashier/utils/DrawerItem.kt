package app.snapcart.cashier.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.snapcart.cashier.R

sealed class DrawerItem(@StringRes var title: Int, @DrawableRes var icon: Int) {

    object HelpCenter : DrawerItem(R.string.drawer_title_help_center, R.drawable.ic_help)

    object Settings : DrawerItem(R.string.drawer_title_settings, R.drawable.ic_setting)

    object LogOut : DrawerItem(R.string.drawer_title_logout, R.drawable.ic_logout)
}
