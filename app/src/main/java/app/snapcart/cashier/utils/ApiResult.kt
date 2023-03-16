package app.snapcart.cashier.utils

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiLoading<T : Any> : ApiResult<T>
class ApiIdle<T : Any> : ApiResult<T>
class ApiError<T : Any>(val status: GrpcStatus, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>
