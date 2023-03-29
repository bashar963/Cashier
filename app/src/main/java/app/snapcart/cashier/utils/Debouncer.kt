package app.snapcart.cashier.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class Debouncer(private val coroutineContext: CoroutineContext) {
    private var debounceJob: Job? = null

    fun debounce(delayMillis: Long = 500L, action: suspend () -> Unit) {
        debounceJob?.cancel()
        debounceJob = CoroutineScope(coroutineContext).launch {
            delay(delayMillis)
            action()
        }
    }
}