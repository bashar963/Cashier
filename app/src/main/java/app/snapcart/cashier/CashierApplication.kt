package app.snapcart.cashier

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CashierApplication : Application() {
    override fun onCreate() {
        Lingver.init(this, "id")
        super.onCreate()
    }
}
