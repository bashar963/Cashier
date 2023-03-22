package app.snapcart.cashier.utils

data class Response<T>(val body: T, val status: Status)

sealed interface Status
object StatusSuccess : Status
class StatusError<T>(val message: T) : Status
