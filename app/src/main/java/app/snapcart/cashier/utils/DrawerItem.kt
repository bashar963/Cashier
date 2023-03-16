package app.snapcart.cashier.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.snapcart.cashier.R

enum class DrawerItem(@StringRes val title: Int, @DrawableRes val icon: Int) {
    HelpCenter(R.string.drawer_title_help_center, R.drawable.ic_help),
    Settings(R.string.drawer_title_settings, R.drawable.ic_setting),
    LogOut(R.string.drawer_title_logout, R.drawable.ic_logout)
}
