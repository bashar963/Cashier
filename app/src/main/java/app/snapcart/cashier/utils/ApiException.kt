package app.snapcart.cashier.utils

class ApiException(val status: Any?) : Exception() {

    override fun toString(): String {
        return "Exception: status -> $status"
    }
}