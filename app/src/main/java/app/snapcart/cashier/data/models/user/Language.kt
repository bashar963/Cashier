package app.snapcart.cashier.data.models.user

import androidx.annotation.StringRes
import app.snapcart.cashier.R

enum class Language(val language: String, @StringRes val languageName: Int) {
    ENGLISH("en", R.string.language_dialog_english), INDONESIAN("in", R.string.language_dialog_indonesia)
}
