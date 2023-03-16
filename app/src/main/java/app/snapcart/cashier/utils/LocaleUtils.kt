package app.snapcart.cashier.utils

import android.content.Context
import com.yariksoffice.lingver.Lingver

object LocaleUtils {

    fun setLocale(context: Context, language: String) = updateResources(context, language)

    private fun updateResources(context: Context, language: String) {
        Lingver.getInstance().setLocale(context, language)
    }
}
