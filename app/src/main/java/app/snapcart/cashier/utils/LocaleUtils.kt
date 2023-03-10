package app.snapcart.cashier.utils

import android.content.Context
import java.util.Locale

object LocaleUtils {

    fun setLocale(context: Context, language: String) = updateResources(context, language)

    private fun updateResources(context: Context, language: String) {
        context.resources.apply {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.setLocale(locale)
            context.createConfigurationContext(configuration)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
    }
}
