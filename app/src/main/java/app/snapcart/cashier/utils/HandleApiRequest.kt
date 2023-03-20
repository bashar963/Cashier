package app.snapcart.cashier.utils

suspend fun <T : Any> handleApiRequest(
    request: suspend () -> GrpcResponse<T>
): ApiResult<T> {
    return try {
        val response = request.invoke()
        val body = response.body
        if (response.status == GrpcStatusSuccess) {
            ApiSuccess(body)
        } else {
            ApiError(status = response.status, message = "")
        }
    } catch (e: Throwable) {
        ApiException(e)
    }
}
