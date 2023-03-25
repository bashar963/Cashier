package app.snapcart.cashier

import android.app.Application
import app.snapcart.cashier.utils.logging.CashierDebugTree
import app.snapcart.cashier.utils.logging.CrashlyticsTree
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

private const val DEFAULT_LANGUAGE = "id"

@HiltAndroidApp
class CashierApplication : Application() {
    override fun onCreate() {
        Lingver.init(this, DEFAULT_LANGUAGE)
        super.onCreate()
        configureDebugSettings()
        configureCrashlytics()
    }

    private fun configureDebugSettings() {
        if (BuildConfig.DEBUG) {
            Timber.plant(CashierDebugTree())
        }
    }

    private fun configureCrashlytics() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCrashlyticsCollectionEnabled(BuildConfig.CRASHLYTICS_ENABLED)
        Timber.plant(CrashlyticsTree(crashlytics))
    }
}
