package app.snapcart.cashier.utils

suspend fun <T : Any> handleApiRequest(
    request: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = request.invoke()
        val body = response.body
        if (response.status == StatusSuccess) {
            Result.success(body)
        } else {
            val error = response.status as StatusError<*>
            Result.failure(ApiException(status = error.message))
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
