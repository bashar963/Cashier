package app.snapcart.cashier.utils.logging

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CrashlyticsTree(
    private val crashlytics: FirebaseCrashlytics
) : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        // intercept creation of a tag
        return createTag()
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // usually it's connection errors, we don't need them in logs
        if (t is UnknownHostException || t is SocketTimeoutException) {
            return
        }

        if (message.isNotEmpty()) {
            crashlytics.log("$tag: $message")
        }
        t?.let {
            crashlytics.recordException(t)
        }
    }
}
