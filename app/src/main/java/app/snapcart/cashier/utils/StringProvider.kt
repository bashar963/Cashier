package app.snapcart.cashier.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface StringProvider {

    fun getString(@StringRes stringId: Int): String

}

class CashierStringProvider @Inject constructor(
    private val context: Context
) : StringProvider {
    override fun getString(stringId: Int): String {
        return context.getString(stringId)
    }
}