package app.snapcart.cashier.utils

data class GrpcResponse(val body: String, val status: GrpcStatus)

sealed interface GrpcStatus
object GrpcStatusSuccess : GrpcStatus
class GrpcStatusError<T>(val message: T) : GrpcStatus
