package app.snapcart.cashier.utils

suspend fun handleApiRequest(
    request: suspend () -> GrpcResponse
): ApiResult<String> {
    return try {
        val response = request.invoke()
        val body = response.body
        if (response.status == GrpcStatusSuccess) {
            ApiSuccess(body)
        } else {
            ApiError(status = response.status, message = response.body)
        }
    } catch (e: Throwable) {
        ApiException(e)
    }
}
