package app.snapcart.cashier.utils.logging

import timber.log.Timber

/** A [Timber.Tree] for debug builds. Automatically infers the tag from the calling class. */
class CashierDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        // intercept creation of a tag
        return createTag()
    }
}
