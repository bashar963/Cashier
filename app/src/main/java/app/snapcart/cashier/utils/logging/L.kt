package app.snapcart.cashier.utils.logging

import timber.log.Timber
import java.util.regex.Matcher
import java.util.regex.Pattern

object L {

    fun v(message: String?, vararg args: Any?) {
        Timber.v(message, *args)
    }

    /** Log a verbose exception and a message with optional format args. */
    fun v(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.v(t, message, *args)
    }

    /** Log a verbose exception. */
    fun v(t: Throwable?) {
        Timber.v(t)
    }

    /** Log a debug message with optional format args. */
    fun d(message: String?, vararg args: Any?) {
        Timber.d(message, *args)
    }

    /** Log a debug exception and a message with optional format args. */
    fun d(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.d(t, message, *args)
    }

    /** Log a debug exception. */
    fun d(t: Throwable?) {
        Timber.d(t)
    }

    /** Log an info message with optional format args. */
    fun i(message: String?, vararg args: Any?) {
        Timber.i(message, *args)
    }

    /** Log an info exception and a message with optional format args. */
    fun i(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.i(t, message, *args)
    }

    /** Log an info exception. */
    fun i(t: Throwable?) {
        Timber.i(t)
    }

    /** Log a warning message with optional format args. */
    fun w(message: String?, vararg args: Any?) {
        Timber.w(message, *args)
    }

    /** Log a warning exception and a message with optional format args. */
    fun w(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.w(t, message, *args)
    }

    /** Log a warning exception. */
    fun w(t: Throwable?) {
        Timber.w(t)
    }

    /** Log an error message with optional format args. */
    fun e(message: String?, vararg args: Any?) {
        Timber.e(message, *args)
    }

    /** Log an error exception and a message with optional format args. */
    fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.e(t, message, *args)
    }

    /** Log an error exception. */
    fun e(t: Throwable?) {
        Timber.e(t)
    }

    /** Log an assert message with optional format args. */
    fun wtf(message: String?, vararg args: Any?) {
        Timber.wtf(message, *args)
    }

    /** Log an assert exception and a message with optional format args. */
    fun wtf(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.wtf(t, message, *args)
    }

    /** Log an assert exception. */
    fun wtf(t: Throwable?) {
        Timber.wtf(t)
    }

    /** Log at `priority` a message with optional format args. */
    fun log(priority: Int, message: String?, vararg args: Any?) {
        Timber.log(priority, message, *args)
    }

    /** Log at `priority` an exception. */
    fun log(priority: Int, t: Throwable?) {
        Timber.log(priority, t)
    }
}

private val ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$")

fun createTag(): String {
    var tag = Throwable("").stackTrace[7].className
    val matcher: Matcher = ANONYMOUS_CLASS.matcher(tag)
    if (matcher.find()) {
        tag = matcher.replaceAll("")
    }
    return tag.substringAfterLast('.')
}
